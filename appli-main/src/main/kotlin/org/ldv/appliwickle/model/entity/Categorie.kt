package org.ldv.appliwickle.model.entity

import jakarta.persistence.*

@Entity
class Categorie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var nom: String,

    @Column(length = 500)
    var description: String? = null,

    // Relation
    @OneToMany(mappedBy = "categorie", cascade = [CascadeType.ALL])
    var produits: MutableList<Produit> = mutableListOf()
)