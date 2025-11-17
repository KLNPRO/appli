package org.ldv.appliwickle.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Avis(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var note: Int, // Entre 1 et 5

    @Column(length = 1000)
    var commentaire: String? = null,

    @Column(nullable = false)
    var dateAvis: LocalDate = LocalDate.now(),

    // Relations
    //@ManyToOne
    //@JoinColumn(name = "utilisateur_id", nullable = false)
    //var utilisateur: Utilisateur,

    //@ManyToOne
    //@JoinColumn(name = "produit_id", nullable = false)
    //var produit: Produit
)