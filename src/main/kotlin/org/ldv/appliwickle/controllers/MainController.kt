package org.ldv.appliwickle.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/appli-wickle")
    fun home(): String {
        return "index"

    }
    @GetMapping("/wickle/a-propos")
    fun aPropos(): String {
        return "pageVisiteur/a-propos"
    }

    @GetMapping("/wickle/contact")
    fun contact(): String {
        return "contact"
    }

    /**
     * Page Inscription
     */
    @GetMapping("/wickle/inscription")
    fun inscription(): String {
        return "inscription"
    }

    /**
     * Page Connexion
     */
    @GetMapping("/wickle/connexion")
    fun connexion(): String {
        return "pageVisiteur/connexion"
    }

    /**
     * Page Liste des produits
     */
    @GetMapping("/wickle/produits")
    fun produits(): String {
        return "produits"
    }

    /**
     * Page RGPD
     */
    @GetMapping("/wickle/rgpd")
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