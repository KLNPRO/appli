package org.ldv.appliwickle.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl
import java.io.Serializable

@Embeddable

data class LignePanierId(
    @Column(name = "panier_id")
    var panierId: Long? = null,

    @Column(name = "variante_id")
    var varianteId: Long? = null
) : Serializable


