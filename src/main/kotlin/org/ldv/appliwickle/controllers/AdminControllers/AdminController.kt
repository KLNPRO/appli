package org.ldv.appliwickle.controllers.AdminControllers

import org.springframework.web.bind.annotation.GetMapping

class AdminController {
    /**
     * Page d'accueil du back-office
     */
    @GetMapping("/wickle/admin")
    fun index(): String {
        return "pageAdmin/index"
    }
}