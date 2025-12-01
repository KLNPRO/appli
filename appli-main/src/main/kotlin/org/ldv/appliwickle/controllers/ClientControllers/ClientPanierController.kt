package org.ldv.appliwickle.controllers.ClientControllers

import org.ldv.appliwickle.model.dao.LignePanierDAO
import org.ldv.appliwickle.model.dao.PanierDAO
import org.ldv.appliwickle.model.dao.UtilisateurDAO
import org.ldv.appliwickle.model.dao.VarianteDAO
import org.ldv.appliwickle.model.entity.LignePanier
import org.ldv.appliwickle.model.entity.LignePanierId
import org.ldv.appliwickle.model.entity.Panier
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDate

@Controller
@RequestMapping("/wickle/client/panier")
class ClientPanierController(private val panierDAO: PanierDAO,
                             private val lignePanierDAO: LignePanierDAO,
                             private val utilisateurDAO: UtilisateurDAO,
                             private val varianteDAO: VarianteDAO
) {
    /**
     * Affiche le panier du client connecté
     */
    @GetMapping
    fun index(authentication: Authentication, model: Model): String {
        val utilisateur = utilisateurDAO.findByEmail(authentication.name)
            ?: throw IllegalStateException("Utilisateur non trouvé")

        // Récupérer ou créer le panier
        var panier = utilisateur.panier
        if (panier == null) {
            panier = Panier(
                utilisateur = utilisateur,
                dateCreation = LocalDate.now()
            )
            panierDAO.save(panier)
            utilisateur.panier = panier
            utilisateurDAO.save(utilisateur)
        }

        // Calculer le total
        val total = panier.lignesPanier.sumOf {
            it.variante.produit.prix * it.quantite
        }

        model.addAttribute("panier", panier)
        model.addAttribute("total", total)

        return "pageClient/panier"
    }
    /**
     * Ajoute un produit au panier
     */
    @PostMapping("/ajouter")
    fun ajouter(
        @RequestParam varianteId: Long,
        @RequestParam(defaultValue = "1") quantite: Int,
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val utilisateur = utilisateurDAO.findByEmail(authentication.name)
                ?: throw IllegalStateException("Utilisateur non trouvé")

            val variante = varianteDAO.findById(varianteId.toInt()).orElseThrow {
                IllegalArgumentException("Variante introuvable")
            }

            // Vérifier le stock
            if (variante.stock < quantite) {
                redirectAttributes.addFlashAttribute("error", "Stock insuffisant")
                return "redirect:/wickle/visiteur/produits/${variante.produit.id}"
            }

            // Récupérer ou créer le panier
            var panier = utilisateur.panier
            if (panier == null) {
                panier = Panier(
                    utilisateur = utilisateur,
                    dateCreation = LocalDate.now()
                )
                panierDAO.save(panier)
                utilisateur.panier = panier
                utilisateurDAO.save(utilisateur)
            }

            // Vérifier si la variante est déjà dans le panier
            val ligneExistante = panier.lignesPanier.find {
                it.variante.id == varianteId
            }

            if (ligneExistante != null) {
                // Mettre à jour la quantité
                ligneExistante.quantite += quantite
                lignePanierDAO.save(ligneExistante)
            } else {
                // Créer une nouvelle ligne
                val lignePanierId = LignePanierId(
                    panierId = panier.id,
                    varianteId = varianteId
                )

                val nouvelleLigne = LignePanier(
                    id = lignePanierId,
                    panier = panier,
                    variante = variante,
                    quantite = quantite
                )

                lignePanierDAO.save(nouvelleLigne)
            }

            redirectAttributes.addFlashAttribute("success", "Produit ajouté au panier !")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur : ${e.message}")
        }

        return "redirect:/wickle/client/panier"
    }

    /**
     * Modifie la quantité d'une ligne du panier
     */
    @PostMapping("/modifier")
    fun modifier(
        @RequestParam varianteId: Long,
        @RequestParam quantite: Int,
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val utilisateur = utilisateurDAO.findByEmail(authentication.name)
                ?: throw IllegalStateException("Utilisateur non trouvé")

            val panier = utilisateur.panier
                ?: throw IllegalStateException("Panier introuvable")

            val lignePanierId = LignePanierId(
                panierId = panier.id,
                varianteId = varianteId
            )

            val ligne = lignePanierDAO.findById(lignePanierId).orElseThrow {
                IllegalArgumentException("Ligne de panier introuvable")
            }

            if (quantite <= 0) {
                lignePanierDAO.delete(ligne)
                redirectAttributes.addFlashAttribute("success", "Produit retiré du panier")
            } else {
                ligne.quantite = quantite
                lignePanierDAO.save(ligne)
                redirectAttributes.addFlashAttribute("success", "Quantité mise à jour")
            }
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur : ${e.message}")
        }

        return "redirect:/wickle/client/panier"
    }

    /**
     * Supprime une ligne du panier
     */
    @GetMapping("/supprimer/{varianteId}")
    fun supprimer(
        @PathVariable varianteId: Long,
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val utilisateur = utilisateurDAO.findByEmail(authentication.name)
                ?: throw IllegalStateException("Utilisateur non trouvé")

            val panier = utilisateur.panier
                ?: throw IllegalStateException("Panier introuvable")

            val lignePanierId = LignePanierId(
                panierId = panier.id,
                varianteId = varianteId
            )

            lignePanierDAO.deleteById(lignePanierId)
            redirectAttributes.addFlashAttribute("success", "Produit retiré du panier")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur : ${e.message}")
        }

        return "redirect:/wickle/client/panier"
    }

    /**
     * Vide complètement le panier
     */
    @GetMapping("/vider")
    fun vider(
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val utilisateur = utilisateurDAO.findByEmail(authentication.name)
                ?: throw IllegalStateException("Utilisateur non trouvé")

            val panier = utilisateur.panier
                ?: throw IllegalStateException("Panier introuvable")

            panier.lignesPanier.clear()
            panierDAO.save(panier)

            redirectAttributes.addFlashAttribute("success", "Panier vidé")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur : ${e.message}")
        }

        return "redirect:/wickle/client/panier"
    }
}