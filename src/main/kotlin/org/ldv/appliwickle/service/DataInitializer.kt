package org.ldv.appliwickle.service

import org.ldv.appliwickle.model.dao.CategorieDAO
import org.ldv.appliwickle.model.entity.Categorie
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val categorieDAO: CategorieDAO,
    //private val articleDAO: ArticleDAO
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        // Vérifie si la base contient déjà des données
        if (categorieDAO.count() > 0 ) {
            println("ℹ️ Données déjà présentes, initialisation ignorée.")
            return
        }

        println("🚀 Initialisation des données...")

        // === Catégories ===
        val catGadget = Categorie(nom = "Gadgets", description = "")
        val catJouet = Categorie(nom = "Jouets",description = "")
        val catMaison = Categorie(nom = "Maison",description = "")

        categorieDAO.saveAll(listOf(catGadget, catJouet, catMaison))

        // === Articles ===
/**
        val articleMontre = Article(
            nom = "Montre connectée",
            description = "Montre connectée avec capteur de fréquence cardiaque et suivi d’activité.",
            stock = 20,
            prix = 79.99,
            lienImage = "https://example.com/images/montre-connectee.jpg",
            categorie = catGadget
        )

        val articleDrone = Article(
            nom = "Mini drone",
            description = "Drone compact avec caméra HD et contrôle via smartphone.",
            stock = 15,
            prix = 149.99,
            lienImage = "https://example.com/images/mini-drone.jpg",
            categorie = catGadget
        )


        // === Sauvegarde des articles ===
        articleDAO.saveAll(
            listOf(
                articleMontre,
                articleDrone

            )
        )
**/
       // println("✅ Données initiales insérées : ${categorieDAO.count()} catégories, ${articleDAO.count()} articles.")
    }
}


