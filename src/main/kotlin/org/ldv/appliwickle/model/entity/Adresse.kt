package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class Adresse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var rue: String,

    @Column(nullable = false)
    var codePostal: String,

    @Column(nullable = false)
    var ville: String,

    @Column(nullable = false)
    var pays: String = "France",

    var complement: String? = null,

    // Relation
    //@ManyToOne
   // @JoinColumn(name = "utilisateur_id", nullable = false)
    //var utilisateur: Utilisateur
)