package org.ldv.appliwickle.model.dao

import org.ldv.appliwickle.model.entity.Commande
import org.springframework.data.jpa.repository.JpaRepository

interface CommandeDAO: JpaRepository<Commande, Int>
{
}