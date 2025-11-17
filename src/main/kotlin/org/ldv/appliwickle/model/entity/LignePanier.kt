package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

//@Entity
class LignePanier (
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    //@Column(nullable = false)
    var quantite: Int = 1,

    // Relations
   // @ManyToOne
    //@JoinColumn(name = "panier_id", nullable = false)
    var panier: Panier,

    //@ManyToOne
    //@JoinColumn(name = "variante_id", nullable = false)
    var variante: Variante

    )