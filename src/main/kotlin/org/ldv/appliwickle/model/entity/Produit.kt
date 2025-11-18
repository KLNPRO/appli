package org.ldv.appliwickle.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Produit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nom: String,

    @Column(length = 1000, nullable = false)
    var description: String,

    @Column(nullable = false)
    var prix: Double,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false)
    var dateAjout: LocalDate = LocalDate.now(),

    // Relations
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    var categorie: Categorie,

    @OneToMany(mappedBy = "produit", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: MutableList<Image> = mutableListOf(),

    @OneToMany(mappedBy = "produit", cascade = [CascadeType.ALL], orphanRemoval = true)
    var variantes: MutableList<Image> = mutableListOf(),

    @OneToMany(mappedBy = "produit", cascade = [CascadeType.ALL], orphanRemoval = true)
    var avis: MutableList<Image> = mutableListOf()
)