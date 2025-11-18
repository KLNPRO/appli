package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class LigneCommande(

    @EmbeddedId
    var id: LigneCommandeId,
    @Column(nullable = false)
    var quantite: Int,

    @Column(nullable = false)
    var prixUnitaire: Double,

    // Relations
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @MapsId(value = "commandeId")
    var commande: Commande,

    @ManyToOne
    @MapsId(value = "varianteId")
    @JoinColumn(name = "variante_id", nullable = false)
    var variante: Variante
)