package it.polito.wa2.g13.crmanalytics.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Profile("!no-security")
class SecurityConfig(
    private val environment: Environment,
) {
    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                // If api-docs is enabled allow resource access to get the api
                if (environment.acceptsProfiles(Profiles.of("api-docs"))) {
                    it.pathMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                }

                it
                    .pathMatchers(
                        HttpMethod.GET,
                        "/API/contacts/**",
                        "/API/professionals/**",
                        "/API/customers/**",
                        "/API/joboffers/**"
                    )
                    .permitAll()

                it.pathMatchers(HttpMethod.GET).hasRole("OPERATOR")
                it.pathMatchers(HttpMethod.POST).hasRole("OPERATOR")
                it.pathMatchers(HttpMethod.PUT).hasRole("OPERATOR")
                it.pathMatchers(HttpMethod.DELETE).hasRole("ADMIN")

                it.anyExchange().authenticated()
            }
            .oauth2ResourceServer { it.jwt {} }
            .csrf { it.disable() }
            .cors { it.disable() }
            .build()
    }

    /**
     * Get roles from the jwt
     */
    @Bean
    fun jwtAuthenticationConverterForKeycloak(): JwtAuthenticationConverter =
        JwtAuthenticationConverter().apply {
            setJwtGrantedAuthoritiesConverter { jwt ->
                val realmAccess = jwt.getClaim<Map<String, Collection<String>>>("realm_access")

                realmAccess["roles"]?.map { SimpleGrantedAuthority("ROLE_$it") }
            }
        }
}

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Profile("no-security")
class SecurityNoFilterConfig {
    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                it.anyExchange().permitAll()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .build()
    }
}
