package org.ldv.appliwickle.service

import org.ldv.appliwickle.model.dao.AvisDAO
import org.ldv.appliwickle.model.dao.CategorieDAO
import org.ldv.appliwickle.model.dao.CommandeDAO
import org.ldv.appliwickle.model.dao.ImageDAO
import org.ldv.appliwickle.model.dao.PanierDAO
import org.ldv.appliwickle.model.dao.ProduitDAO
import org.ldv.appliwickle.model.dao.UtilisateurDAO
import org.ldv.appliwickle.model.dao.VarianteDAO
import org.ldv.appliwickle.model.entity.Avis
import org.ldv.appliwickle.model.entity.Categorie
import org.ldv.appliwickle.model.entity.Commande
import org.ldv.appliwickle.model.entity.Image
import org.ldv.appliwickle.model.entity.Panier
import org.ldv.appliwickle.model.entity.Produit
import org.ldv.appliwickle.model.entity.Role
import org.ldv.appliwickle.model.entity.StatutCommande
import org.ldv.appliwickle.model.entity.TailleProduit
import org.ldv.appliwickle.model.entity.Utilisateur
import org.ldv.appliwickle.model.entity.Variante
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer(
    private val categorieDAO: CategorieDAO,
    private val utilisateurDAO: UtilisateurDAO,
    private val produitDAO: ProduitDAO,
    private val imageDAO: ImageDAO,
    private val varianteDAO: VarianteDAO,
    private val panierDAO: PanierDAO,
    private val commandeDAO: CommandeDAO,
    private val avisDAO: AvisDAO,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    //private val passwordEncoder = BCryptPasswordEncoder()

    override fun run(vararg args: String?) {

        // Vérifie si la base contient déjà des données
        if (categorieDAO.count() > 0 || produitDAO.count() > 0) {
            println("ℹ️ Données déjà présentes, initialisation ignorée.")
            return
        }


        println("🚀 Initialisation des données Wickle...")

        // === UTILISATEURS ===
        val admin = Utilisateur(
            nom = "Admin",
            prenom = "Wickle",
            email = "admin@wickle.fr",
            motDePasse = passwordEncoder.encode("admin123"), // ⚠️ MOT DE PASSE HASHÉ
            telephone = "0612345678",
            role = Role.ADMIN,
            adresse = "",
            dateInscription = LocalDate.now()
        )
        utilisateurDAO.save(admin)

        val client1 = Utilisateur(
            nom = "Dupont",
            prenom = "Marie",
            email = "marie.dupont@example.com",
            motDePasse = passwordEncoder.encode("client123"), // ⚠️ MOT DE PASSE HASHÉ
            telephone = "0623456789",
            role = Role.CLIENT,
            adresse = "",
            dateInscription = LocalDate.now().minusDays(30)
        )
        utilisateurDAO.save(client1)
        val client2 = Utilisateur(
            nom = "Martin",
            prenom = "Pierre",
            email = "pierre.martin@example.com",
            motDePasse = passwordEncoder.encode("client123"), // ⚠️ MOT DE PASSE HASHÉ
            telephone = "0634567890",
            role = Role.CLIENT,
            adresse = "",
            dateInscription = LocalDate.now().minusDays(15)
        )
        utilisateurDAO.save(client2)

        // === PANIERS ===
        val panierClient1 = Panier(
            utilisateur = client1,
            dateCreation = LocalDate.now()
        )
        panierDAO.save(panierClient1)

        val panierClient2 = Panier(
            utilisateur = client2,
            dateCreation = LocalDate.now()
        )
        panierDAO.save(panierClient2)


        // === CATÉGORIES ===
        val catHomme = Categorie(
            nom = "Homme",
            description = "Vêtements pour homme de seconde main"
        )
        categorieDAO.save(catHomme)

        val catFemme = Categorie(
            nom = "Femme",
            description = "Vêtements pour femme de seconde main"
        )
        categorieDAO.save(catFemme)

        val catEnfant = Categorie(
            nom = "Enfant",
            description = "Vêtements pour enfant de seconde main"
        )
        categorieDAO.save(catEnfant)

        val catAccessoires = Categorie(
            nom = "Accessoires",
            description = "Accessoires de mode de seconde main"
        )
        categorieDAO.save(catAccessoires)

        // === PRODUITS HOMME ===
        val produit1 = Produit(
            nom = "T-shirt Vintage Blanc",
            description = "T-shirt vintage en coton, parfait état, style décontracté",
            prix = 15.99,
            stock = 50,
            categorie = catHomme,
            dateAjout = LocalDate.now().minusDays(10)
        )
        produitDAO.save(produit1)

        // Images pour T-shirt
        imageDAO.save(Image(
            url = "/img/produits/tshirt-blanc-1.jpg",
            estPrincipale = true,
            produit = produit1
        ))
        imageDAO.save(Image(
            url = "/img/produits/tshirt-blanc-2.jpg",
            estPrincipale = false,
            produit = produit1
        ))

        // Variantes pour T-shirt
        varianteDAO.saveAll(listOf(
            Variante(couleur = "Blanc", taille = TailleProduit.M, stock = 15, produit = produit1),
            Variante(couleur = "Blanc", taille = TailleProduit.L, stock = 20, produit = produit1),
            Variante(couleur = "Blanc", taille = TailleProduit.XL, stock = 15, produit = produit1)
        ))

        val produit2 = Produit(
            nom = "Jean Slim Noir",
            description = "Jean slim noir en excellent état, coupe moderne",
            prix = 35.99,
            stock = 30,
            categorie = catHomme,
            dateAjout = LocalDate.now().minusDays(5)
        )
        produitDAO.save(produit2)

        imageDAO.save(Image(
            url = "/img/produits/jean-noir-1.jpg",
            estPrincipale = true,
            produit = produit2
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Noir", taille = TailleProduit.M, stock = 10, produit = produit2),
            Variante(couleur = "Noir", taille = TailleProduit.L, stock = 12, produit = produit2),
            Variante(couleur = "Noir", taille = TailleProduit.XL, stock = 8, produit = produit2)
        ))

        val produit3 = Produit(
            nom = "Chemise à Carreaux",
            description = "Chemise à carreaux bleu et blanc, style décontracté",
            prix = 22.50,
            stock = 25,
            categorie = catHomme,
            dateAjout = LocalDate.now().minusDays(8)
        )
        produitDAO.save(produit3)

        imageDAO.save(
            Image(
                url = "/img/produits/chemise-carreaux-1.jpg",
                estPrincipale = true,
                produit = produit3
            )
        )

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Bleu/Blanc", taille = TailleProduit.S, stock = 8, produit = produit3),
            Variante(couleur = "Bleu/Blanc", taille = TailleProduit.M, stock = 10, produit = produit3),
            Variante(couleur = "Bleu/Blanc", taille = TailleProduit.L, stock = 7, produit = produit3)
        ))
        // === PRODUITS FEMME ===
        val produit4 = Produit(
            nom = "Robe d'Été Fleurie",
            description = "Robe légère à motifs floraux, parfaite pour l'été",
            prix = 28.99,
            stock = 40,
            categorie = catFemme,
            dateAjout = LocalDate.now().minusDays(12)
        )
        produitDAO.save(produit4)

        imageDAO.save(Image(
            url = "/img/produits/robe-fleurie-1.jpg",
            estPrincipale = true,
            produit = produit4
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Rose", taille = TailleProduit.S, stock = 12, produit = produit4),
            Variante(couleur = "Rose", taille = TailleProduit.M, stock = 15, produit = produit4),
            Variante(couleur = "Rose", taille = TailleProduit.L, stock = 13, produit = produit4)
        ))

        val produit5 = Produit(
            nom = "Pull en Laine Beige",
            description = "Pull doux en laine mérinos, parfait pour l'hiver",
            prix = 32.50,
            stock = 20,
            categorie = catFemme,
            dateAjout = LocalDate.now().minusDays(7)
        )
        produitDAO.save(produit5)

        imageDAO.save(Image(
            url = "/img/produits/pull-beige-1.jpg",
            estPrincipale = true,
            produit = produit5
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Beige", taille = TailleProduit.S, stock = 6, produit = produit5),
            Variante(couleur = "Beige", taille = TailleProduit.M, stock = 8, produit = produit5),
            Variante(couleur = "Beige", taille = TailleProduit.L, stock = 6, produit = produit5)
        ))

        val produit6 = Produit(
            nom = "Jupe Midi Noire",
            description = "Jupe élégante longueur midi, style intemporel",
            prix = 24.99,
            stock = 35,
            categorie = catFemme,
            dateAjout = LocalDate.now().minusDays(6)
        )
        produitDAO.save(produit6)

        imageDAO.save(Image(
            url = "/img/produits/jupe-noire-1.jpg",
            estPrincipale = true,
            produit = produit6
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Noir", taille = TailleProduit.S, stock = 10, produit = produit6),
            Variante(couleur = "Noir", taille = TailleProduit.M, stock = 15, produit = produit6),
            Variante(couleur = "Noir", taille = TailleProduit.L, stock = 10, produit = produit6)
        ))

        // === PRODUITS ENFANT ===
        val produit7 = Produit(
            nom = "Sweat à Capuche Enfant",
            description = "Sweat confortable pour enfant, couleur vive",
            prix = 18.99,
            stock = 45,
            categorie = catEnfant,
            dateAjout = LocalDate.now().minusDays(4)
        )
        produitDAO.save(produit7)

        imageDAO.save(Image(
            url = "/img/produits/sweat-enfant-1.jpg",
            estPrincipale = true,
            produit = produit7
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Rouge", taille = TailleProduit.S, stock = 15, produit = produit7),
            Variante(couleur = "Rouge", taille = TailleProduit.M, stock = 18, produit = produit7),
            Variante(couleur = "Bleu", taille = TailleProduit.S, stock = 12, produit = produit7)
        ))
        // === ACCESSOIRES ===
        val produit8 = Produit(
            nom = "Écharpe en Laine",
            description = "Écharpe chaude et douce en laine naturelle",
            prix = 12.99,
            stock = 60,
            categorie = catAccessoires,
            dateAjout = LocalDate.now().minusDays(3)
        )
        produitDAO.save(produit8)

        imageDAO.save(Image(
            url = "/img/produits/echarpe-laine-1.jpg",
            estPrincipale = true,
            produit = produit8
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Gris", taille = TailleProduit.M, stock = 20, produit = produit8),
            Variante(couleur = "Noir", taille = TailleProduit.M, stock = 20, produit = produit8),
            Variante(couleur = "Beige", taille = TailleProduit.M, stock = 20, produit = produit8)
        ))

        val produit9 = Produit(
            nom = "Sac à Main Cuir",
            description = "Sac à main en cuir véritable, style vintage",
            prix = 45.00,
            stock = 15,
            categorie = catAccessoires,
            dateAjout = LocalDate.now().minusDays(9)
        )
        produitDAO.save(produit9)

        imageDAO.save(Image(
            url = "/img/produits/sac-cuir-1.jpg",
            estPrincipale = true,
            produit = produit9
        ))

        varianteDAO.saveAll(listOf(
            Variante(couleur = "Marron", taille = TailleProduit.M, stock = 8, produit = produit9),
            Variante(couleur = "Noir", taille = TailleProduit.M, stock = 7, produit = produit9)
        ))
        // === AVIS ===
        avisDAO.saveAll(listOf(
            Avis(
                note = 5,
                commentaire = "Excellent produit ! Qualité au top, très satisfait de mon achat.",
                dateAvis = LocalDate.now().minusDays(5),
                utilisateur = client1,
                produit = produit1
            ),
            Avis(
                note = 4,
                commentaire = "Très bon rapport qualité/prix, conforme à la description.",
                dateAvis = LocalDate.now().minusDays(3),
                utilisateur = client2,
                produit = produit4
            ),
            Avis(
                note = 5,
                commentaire = "Parfait ! Livraison rapide et produit en excellent état.",
                dateAvis = LocalDate.now().minusDays(2),
                utilisateur = client1,
                produit = produit5
            )
        ))
        // === COMMANDE EXEMPLE ===
        val varianteCommande1 = varianteDAO.findAll().first()
        val commande1 = Commande(
            numero = "CMD-${System.currentTimeMillis()}",
            dateCommande = LocalDate.now().minusDays(7),
            montantTotal = 48.98,
            statut = StatutCommande.LIVREE,
            utilisateur = client1,
            adresseLivraison = "123 rue de Paris, 75007 Paris",
        )
        commandeDAO.save(commande1)


       println("✅ Données initiales insérées : ${categorieDAO.count()} catégories, ${produitDAO.count()} Produits.")
        println("   📊 ${utilisateurDAO.count()} utilisateurs")
        println("   📦 ${categorieDAO.count()} catégories")
        println("   👕 ${produitDAO.count()} produits")
        println("   🎨 ${varianteDAO.count()} variantes")
        println("   🖼️ ${imageDAO.count()} images")
        println("   ⭐ ${avisDAO.count()} avis")
        println("   🛒 ${commandeDAO.count()} commandes")
    }
}


