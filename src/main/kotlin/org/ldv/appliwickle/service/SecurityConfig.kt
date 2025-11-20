package org.ldv.appliwickle.service

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // TODO: Réactiver plus tard avec les tokens CSRF

            // Restriction des endpoints en fonction du rôle
            .authorizeHttpRequests {
                // Pages publiques accessibles à tous
                it.requestMatchers(
                    "/wickle",
                    "/wickle/connexion",
                    "/wickle/inscription",
                    "/wickle/a-propos",
                    "/wickle/contact",
                    "/wickle/produits",
                    "/wickle/homme",
                    "/wickle/femme",
                    "/wickle/enfant",
                    "/wickle/comment-ca-marche",
                    "/wickle/recherche",
                    "/wickle/faq",
                    "/wickle/livraison",
                    "/wickle/retours",
                    "/wickle/cgv",
                    "/wickle/mentions-legales",
                    "/wickle/rgpd",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/favicon.ico"
                ).permitAll()

                    // Pages admin (réservées au rôle ADMIN)
                    .requestMatchers("/wickle/admin/**").hasRole("ADMIN")

                    // Pages client (réservées au rôle CLIENT)
                    .requestMatchers("/wickle/client/**").hasRole("CLIENT")

                    // Toutes les autres requêtes doivent être authentifiées
                    .anyRequest().authenticated()
            }

            // Configuration du formulaire de connexion
            .formLogin { form ->
                form
                    .loginPage("/wickle/connexion")
                    .defaultSuccessUrl("/wickle/profil")
                    .failureUrl("/wickle/connexion?error=true")
                    .permitAll()
            }

            // Configuration du mécanisme de déconnexion
            .logout { logout ->
                logout
                    .logoutUrl("/wickle/logout")
                    .logoutSuccessUrl("/wickle")
                    .permitAll()
            }

        return http.build()
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}