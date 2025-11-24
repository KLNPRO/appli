package org.ldv.appliwickle.controllers.AdminControllers

import org.ldv.appliwickle.model.dao.CategorieDAO
import org.ldv.appliwickle.model.dao.ProduitDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller


class AdminCategorieController(private val categorieDAO: CategorieDAO){
    /**
     * Affiche la liste de toutes les catégories
     */
    @GetMapping("/wickle/admin/categories")
    fun index(model: Model): String {
        val categories = categorieDAO.findAll()

        model.addAttribute("categories", categories)
        return "pageAdmin/categorie/index"
}

}