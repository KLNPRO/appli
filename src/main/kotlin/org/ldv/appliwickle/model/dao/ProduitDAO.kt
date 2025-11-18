package org.ldv.appliwickle.model.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.ldv.appliwickle.model.entity.Produit

interface ProduitDAO: JpaRepository<Produit, Long> {
}