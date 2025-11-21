package org.ldv.appliwickle.controllers

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController {
    @GetMapping("/wickle")
    fun home(): String {
        return "index"

    }
    @GetMapping("/wickle/profil")
    fun profile(authentication: Authentication): String {

        // Récupération des rôles (authorities) de l'utilisateur connecté
        val roles = authentication.authorities.map { it.authority }

        // Si l'utilisateur est admin → redirection vers le dashboard
        if ("ROLE_ADMIN" in roles) {
            return "redirect:/wickle/admin"
        }

        // Sinon → on affiche la page profil client
        return "pageClient/profile"
    }
    @GetMapping("/wickle/connexion")
    fun connexion(@RequestParam error: Boolean?, model: Model): String {
        // Ajoute un attribut "error" au modèle si la requête contient une erreur
        model.addAttribute("error", error == true)
        return "pageVisiteur/connexion"
    }
    @GetMapping("/wickle/a-propos")
    fun aPropos(): String {
        return "pageVisiteur/a-propos"
    }

    @GetMapping("/wickle/contact")
    fun contact(): String {
        return "pageVisiteur/contact"
    }


    /**
     * Page Inscription
     */
    @GetMapping("/wickle/inscription")
    fun inscription(): String {
        return "pageVisiteur/inscription"
    }


    /**
     * Page Liste des produits
     */
    @GetMapping("/wickle/produits")
    fun produits(): String {
        return "pageVisiteur/produits"
    }

    /**
     * Page RGPD
     */
    @GetMapping("/wickle/rgpd")
    fun rgpd(): String {
        return "pageVisiteur/rgpd"
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
        return "pageVisiteur/homme"  // Réutilise la page produits
    }

    /**
     * Page catégorie Femme
     */
    @GetMapping("/wickle/femme")
    fun femme(): String {
        return "pageVisiteur/femme"
    }

    /**
     * Page catégorie Enfant
     */
    @GetMapping("/wickle/enfant")
    fun enfant(): String {
        return "pageVisiteur/enfant"
    }

    @GetMapping("/wickle/comment-ca-marche")
    fun marche(): String {
        return "pageVisiteur/comment-ca-marche"
    }

    @GetMapping("/wickle/recherche")
    fun recherche(): String {
        return "pageVisiteur/recherche"
    }
    // === PAGES DU FOOTER ===

    @GetMapping("/wickle/faq")
    fun faq(): String {
        return "pageVisiteur/faq"
    }

    @GetMapping("/wickle/livraison")
    fun livraison(): String {
        return "pageVisiteur/livraison"
    }

    @GetMapping("/wickle/retours")
    fun retours(): String {
        return "pageVisiteur/retours"
    }

    @GetMapping("/wickle/cgv")
    fun cgv(): String {
        return "pageVisiteur/cgv"
    }

    @GetMapping("/wickle/mentions-legales")
    fun mentionsLegales(): String {
        return "pageVisiteur/mentions-legales"
    }
}
