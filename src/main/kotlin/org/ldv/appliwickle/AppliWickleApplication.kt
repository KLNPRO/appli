package org.ldv.appliwickle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("org.ldv.appliwickle.model.entity")
@EnableJpaRepositories("org.ldv.appliwickle.model.dao")
class AppliWickleApplication

fun main(args: Array<String>) {
    runApplication<AppliWickleApplication>(*args)
}
