package org.ldv.appliwickle.model.entity

import jakarta.persistence.Embeddable
import jakarta.persistence.MapsId
import java.io.Serializable

@Embeddable
class LigneCommandeId (

    var commandeId: Long? = null,

    var varianteId: Long? = null
) {
}