package org.ldv.appliwickle.model.dao

import org.ldv.appliwickle.model.entity.LigneCommande
import org.springframework.data.jpa.repository.JpaRepository

interface LigneCommandeDAO: JpaRepository<LigneCommande, Long> {
}