package org.ldv.appliwickle.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Panier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var dateCreation: LocalDate = LocalDate.now(),

    // Relations
    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false, unique = true)
    var utilisateur: Utilisateur,

    @OneToMany(mappedBy = "panier", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lignesPanier: MutableList<Image> = mutableListOf()
)