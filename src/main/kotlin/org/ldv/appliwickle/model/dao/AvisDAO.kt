package org.ldv.appliwickle.model.dao

import org.ldv.appliwickle.model.entity.Avis
import org.springframework.data.jpa.repository.JpaRepository

interface AvisDAO: JpaRepository<Avis, Int> {

}
