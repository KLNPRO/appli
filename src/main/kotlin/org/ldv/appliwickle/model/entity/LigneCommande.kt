package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

//@Entity
class LigneCommande(
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    //@Column(nullable = false)
    var quantite: Int,

    //@Column(nullable = false)
    var prixUnitaire: Double,

    // Relations
    //@ManyToOne
    //@JoinColumn(name = "commande_id", nullable = false)
    //var commande: Commande,

    //@ManyToOne
    //@JoinColumn(name = "variante_id", nullable = false)
    //var variante: Variante
)