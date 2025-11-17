package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class Variante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var couleur: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var taille: TailleProduit,

    @Column(nullable = false)
    var stock: Int = 0,

    // Relation
    //@ManyToOne
    //@JoinColumn(name = "produit_id", nullable = false)
    //var produit: Produit
)