package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class LignePanier (

    @EmbeddedId
    var id: LignePanierId = LignePanierId(),

    @Column(nullable = false)
    var quantite: Int = 1,

    // Relations
    @ManyToOne
    @MapsId("panierId")
    @JoinColumn(name = "panier_id", nullable = false)
    var panier: Panier,

    @ManyToOne
    @MapsId("varianteId")
    @JoinColumn(name = "variante_id", nullable = false)
    var variante: Variante

    )