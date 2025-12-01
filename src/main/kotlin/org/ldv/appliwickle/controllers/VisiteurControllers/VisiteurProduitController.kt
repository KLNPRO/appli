package org.ldv.appliwickle.controllers.VisiteurControllers

import org.ldv.appliwickle.model.dao.CategorieDAO
import org.ldv.appliwickle.model.dao.ProduitDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/wickle/visiteur")
class VisiteurProduitController(
    private val produitDAO: ProduitDAO,
    private val categorieDAO: CategorieDAO
) {

    /**
     * Affiche tous les produits disponibles
     */
    @GetMapping("/produits")
    fun index(model: Model): String {
        val produits = produitDAO.findAll()
        val categories = categorieDAO.findAll()

        model.addAttribute("produits", produits)
        model.addAttribute("categories", categories)

        return "pageVisiteur/produits-liste"
    }

    /**
     * Affiche le détail d'un produit
     */
    @GetMapping("/produits/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val produit = produitDAO.findById(id).orElseThrow {
            IllegalArgumentException("Produit introuvable avec l'ID: $id")
        }

        model.addAttribute("produit", produit)

        return "pageVisiteur/produit-detail"
    }

    /**
     * Affiche les produits d'une catégorie
     */
    @GetMapping("/produits/categorie/{categorieId}")
    fun parCategorie(@PathVariable categorieId: Int, model: Model): String {
        val categorie = categorieDAO.findById(categorieId).orElseThrow {
            IllegalArgumentException("Catégorie introuvable")
        }

        val produits = categorie.produits
        val categories = categorieDAO.findAll()

        model.addAttribute("produits", produits)
        model.addAttribute("categories", categories)
        model.addAttribute("categorieSelectionnee", categorie)

        return "pageVisiteur/produits-liste"
    }
}