package org.ldv.appliwickle.controllers.AdminControllers

import org.ldv.appliwickle.model.dao.CategorieDAO
import org.ldv.appliwickle.model.dao.ProduitDAO
import org.ldv.appliwickle.model.entity.Produit
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
@RequestMapping("/wickle/admin/produits")
class AdminProduitController(private val produitDAO: ProduitDAO,
                             private val categorieDAO: CategorieDAO
) {
    /**
     * Liste tous les produits
     */
    @GetMapping
    fun index(model: Model): String {
        val produits = produitDAO.findAll()
        model.addAttribute("produits", produits)
        return "pageAdmin/produit/index"
    }

    /**
     * Affiche le détail d'un produit
     */
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val produit = produitDAO.findById(id).orElseThrow {
            IllegalArgumentException("Produit introuvable avec l'ID: $id")
        }
        model.addAttribute("produit", produit)
        return "pageAdmin/produit/show"
    }

    /**
     * Affiche le formulaire de création
     */
    @GetMapping("/create")
    fun create(model: Model): String {
        val categories = categorieDAO.findAll()
        model.addAttribute("categories", categories)
        model.addAttribute("produit", Produit(
            nom = "",
            description = "",
            prix = 0.0,
            stock = 0,
            categorie = categories.firstOrNull() ?: throw IllegalStateException("Aucune catégorie disponible")
        )
        )
        return "pageAdmin/produit/create"
    }

    /**
     * Enregistre un nouveau produit
     */
    @PostMapping("/store")
    fun store(
        @RequestParam nom: String,
        @RequestParam description: String,
        @RequestParam prix: Double,
        @RequestParam stock: Int,
        @RequestParam categorieId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val categorie = categorieDAO.findById(categorieId).orElseThrow {
                IllegalArgumentException("Catégorie introuvable")
            }

            val produit = Produit(
                nom = nom,
                description = description,
                prix = prix,
                stock = stock,
                categorie = categorie,
                dateAjout = LocalDate.now()
            )
            produitDAO.save(produit)
            redirectAttributes.addFlashAttribute("success", "Produit créé avec succès !")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création : ${e.message}")
        }
        return "redirect:/wickle/admin/produits"
    }

    /**
     * Affiche le formulaire de modification
     */
    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val produit = produitDAO.findById(id).orElseThrow {
            IllegalArgumentException("Produit introuvable avec l'ID: $id")
        }
        val categories = categorieDAO.findAll()
        model.addAttribute("produit", produit)
        model.addAttribute("categories", categories)
        return "pageAdmin/produit/edit"
    }

    /**
     * Met à jour un produit existant
     */
    @PostMapping("/update/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam nom: String,
        @RequestParam description: String,
        @RequestParam prix: Double,
        @RequestParam stock: Int,
        @RequestParam categorieId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val produit = produitDAO.findById(id).orElseThrow {
                IllegalArgumentException("Produit introuvable avec l'ID: $id")
            }
            val categorie = categorieDAO.findById(categorieId).orElseThrow {
                IllegalArgumentException("Catégorie introuvable")
            }

            produit.nom = nom
            produit.description = description
            produit.prix = prix
            produit.stock = stock
            produit.categorie = categorie

            produitDAO.save(produit)
            redirectAttributes.addFlashAttribute("success", "Produit modifié avec succès !")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification : ${e.message}")
        }
        return "redirect:/wickle/admin/produits"
    }

    /**
     * Supprime un produit
     */
    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long, redirectAttributes: RedirectAttributes): String {
        try {
            produitDAO.deleteById(id)
            redirectAttributes.addFlashAttribute("success", "Produit supprimé avec succès !")
        } catch (e: Exception) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : ${e.message}")
        }
        return "redirect:/wickle/admin/produits"
    }
}