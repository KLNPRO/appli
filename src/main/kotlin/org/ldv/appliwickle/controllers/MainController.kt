package org.ldv.appliwickle.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/appli-wickle")
    fun home(): String {
        return "index"

    }
    @GetMapping("/appli-wickle/a-propos")
    fun aPropos(): String {
        return "pageVisiteur/a-propos"
    }

    @GetMapping("/appli-wickle/contact")
    fun contact(): String {
        return "contact"
    }

    /**
     * Page Inscription
     */
    @GetMapping("/appli-wickle/inscription")
    fun inscription(): String {
        return "inscription"
    }

    /**
     * Page Connexion
     */
    @GetMapping("/appli-wickle/connexion")
    fun connexion(): String {
        return "pageVisiteur/connexion"
    }

    /**
     * Page Liste des produits
     */
    @GetMapping("/appli-wickle/produits")
    fun produits(): String {
        return "produits"
    }

    /**
     * Page RGPD
     */
    @GetMapping("/appli-wickle/rgpd")
    fun rgpd(): String {
        return "rgpd"
    }

    /**
     * Page Panier
     */
    @GetMapping("/wickle/panier")
    fun panier(): String {
        return "pageVisiteur/panier"
    }

    /**
     * Page catégorie Homme
     */
    @GetMapping("/wickle/homme")
    fun homme(): String {
        return "produits"  // Réutilise la page produits
    }

    /**
     * Page catégorie Femme
     */
    @GetMapping("/wickle/femme")
    fun femme(): String {
        return "produits"
    }

    /**
     * Page catégorie Enfant
     */
    @GetMapping("/wickle/enfant")
    fun enfant(): String {
        return "produits"
    }
}