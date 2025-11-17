package org.ldv.appliwickle.model.entity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Utilisateur(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var nom: String,

    @Column(nullable = false)
    var prenom: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var motDePasse: String,

    var telephone: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role = Role.CLIENT,

    @Column(nullable = false)
    var dateInscription: LocalDate = LocalDate.now(),

    // Relations
    //@OneToMany(mappedBy = "utilisateur", cascade = [CascadeType.ALL])
    //var adresse: MutableList = mutableListOf(),

    //@OneToOne(mappedBy = "utilisateur", cascade = [CascadeType.ALL])
    //var panier: Panier? = null,

   // @OneToMany(mappedBy = "utilisateur", cascade = [CascadeType.ALL])
    //var commande: MutableList = mutableListOf(),

    //@OneToMany(mappedBy = "utilisateur", cascade = [CascadeType.ALL])
    //var avis: MutableList = mutableListOf()
)