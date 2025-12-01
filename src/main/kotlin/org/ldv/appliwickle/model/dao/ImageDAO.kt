package org.ldv.appliwickle.model.dao

import org.ldv.appliwickle.model.entity.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageDAO: JpaRepository<Image, Int> {
}