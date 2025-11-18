package org.ldv.appliwickle.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Commande(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var numero: String,

    @Column(nullable = false)
    var dateCommande: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var montantTotal: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var statut: StatutCommande = StatutCommande.EN_ATTENTE,

    var adresseLivraison: String? = null,

    // Relations
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    var utilisateur: Utilisateur,



    @OneToMany(mappedBy = "commande", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lignesCommande: MutableList<LigneCommande> = mutableListOf()
)