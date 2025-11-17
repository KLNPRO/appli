package org.ldv.appliwickle.model.dao

import org.ldv.appliwickle.model.entity.Categorie
import org.springframework.data.jpa.repository.JpaRepository

interface CategorieDAO : JpaRepository<Categorie,Int> {
}