package org.ldv.appliwickle.model.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.ldv.appliwickle.model.entity.Utilisateur
import org.springframework.data.jpa.repository.Query

interface UtilisateurDAO: JpaRepository<Utilisateur, Long> {
    @Query("select u from Utilisateur u where u.email = ?1")
    fun findByEmail(email: String): Utilisateur?
}