package org.ldv.appliwickle.controllers.ClientControllers

import org.ldv.appliwickle.model.dao.*
import org.ldv.appliwickle.model.entity.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDate

@Controller
@RequestMapping("/wickle/client")
class ClientCommandeController(
    private val commandeDAO: CommandeDAO,
    private val utilisateurDAO: UtilisateurDAO,
    private val panierDAO: PanierDAO,
    private val ligneCommandeDAO: LigneCommandeDAO
) {

    /**
     * Affiche l'historique des commandes du client
     */
    @GetMapping("/commandes")
    fun index(authentication: Authentication, model: Model): String {
        val utilisateur = utilisateurDAO.findByEmail(authentication.name)
            ?: throw IllegalStateException("Utilisateur non trouvé")

        val commandes = utilisateur.commandes.sortedByDescending { it.dateCommande }

        model.addAttribute("commandes", commandes)

        return "pageClient/commandes"
    }

    /**
     * Affiche le détail d'une commande
     */
    @GetMapping("/commandes/{id}")
    fun show(
        @PathVariable id: Long,
        authentication: Authentication,
        model: Model
    ): String {
        val utilisateur = utilisateurDAO.findByEmail(authentication.name)
            ?: throw IllegalStateException("Utilisateur non trouvé")

        val commande = commandeDAO.findById(id.toInt()).orElseThrow {
            IllegalArgumentException("Commande introuvable")
        }

        // Vérifier que la commande appartient bien à l'utilisateur
        if (commande.utilisateur.id != utilisateur.id) {
            throw IllegalAccessException("Accès non autorisé")
        }

        model.addAttribute("commande", commande)

        return "pageClient/commande-detail"
    }

    /**
     * Page de validation de commande
     */
    @GetMapping("/commande/valider")
    fun afficherValidation(authentication: Authentication, model: Model): String {
        val utilisateur = utilisateurDAO.findByEmail(authentication.name)
            ?: throw IllegalStateException("Utilisateur non trouvé")

        val panier = utilisateur.panier
            ?: throw IllegalStateException("Panier introuvable")

        if (panier.lignesPanier.isEmpty()) {
            throw IllegalStateException("Le panier est vide")
        }

        val total = panier.lignesPanier.sumOf {
            it.variante.produit.prix * it.quantite
        }

        model.addAttribute("panier", panier)
        model.addAttribute("total", total)
        model.addAttribute("utilisateur", utilisateur)

        return "pageClient/commande-validation"
    }

    /**
     * Transforme le panier en commande
     */
    @PostMapping("/commande/valider")
    fun valider(
        @RequestParam adresseLivraison: String,
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val utilisateur = utilisateurDAO.findByEmail(authentication.name)
                ?: throw IllegalStateException("Utilisateur non trouvé")

            val panier = utilisateur.panier
                ?: throw IllegalStateException("Panier introuvable")

            if (panier.lignesPanier.isEmpty()) {
                throw IllegalStateException("Le panier est vide")
            }

            // Calculer le montant total
            val montantTotal = panier.lignesPanier.sumOf {
                it.variante.produit.prix * it.quantite
            }

            // Créer la commande
            val numeroCommande = "CMD-${System.currentTimeMillis()}"
            val commande = Commande(
                numero = numeroCommande,
                dateCommande = LocalDate.now(),
                montantTotal = montantTotal,
                statut = StatutCommande.EN_ATTENTE,
                adresseLivraison = adresseLivraison,
                utilisateur = utilisateur
            )

            commandeDAO.save(commande)

            // Convertir les lignes du panier en lignes de commande
            panier.lignesPanier.forEach { lignePanier ->
                val ligneCommandeId = LigneCommandeId(
                    commandeId = commande.id,
                    varianteId = lignePanier.variante.id
                )

                val ligneCommande = LigneCommande(
                    id = ligneCommandeId,
                    commande = commande,
                    variante = lignePanier.variante,
                    quantite = lignePanier.quantite,
                    prixUnitaire = lignePanier.variante.produit.prix
                )

                ligneCommandeDAO.save(ligneCommande)

                // Mettre à jour le stock
                lignePanier.variante.stock -= lignePanier.quantite
            }

            // Vider le panier
            panier.lignesPanier.clear()
            panierDAO.save(panier)

            redirectAttributes.addFlashAttribute("success",
                "Commande validée ! Numéro : $numeroCommande")

            return "redirect:/wickle/client/commandes/${commande.id}"
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur : ${e.message}")
            return "redirect:/wickle/client/panier"
        }
    }
}