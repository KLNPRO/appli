package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var url: String,

    @Column(nullable = false)
    var estPrincipale: Boolean = false,

    // Relation
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    var produit: Produit
)