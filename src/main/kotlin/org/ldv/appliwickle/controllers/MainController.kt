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

    @GetMapping("/appli-wickle/Contact")
    fun contact(): String {
        return "pageVisiteur/Contact"
    }

    /**
     * Page Inscription
     */
    @GetMapping("/appli-wickle/Inscription")
    fun inscription(): String {
        return "pageVisiteur/Inscription"
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
    @GetMapping("/appli-wickle/Produits")
    fun produits(): String {
        return "pageVisiteur/Produits"
    }

    /**
     * Page RGPD
     */
    @GetMapping("/appli-wickle/RGPD")
    fun rgpd(): String {
        return "pageVisiteur/RGPD"
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
        return "pageVisiteur/Produits"  // Réutilise la page produits
    }

    /**
     * Page catégorie Femme
     */
    @GetMapping("/wickle/femme")
    fun femme(): String {
        return "pageVisiteur/Produits"
    }

    /**
     * Page catégorie Enfant
     */
    @GetMapping("/wickle/enfant")
    fun enfant(): String {
        return "pageVisiteur/Produits"
    }
}