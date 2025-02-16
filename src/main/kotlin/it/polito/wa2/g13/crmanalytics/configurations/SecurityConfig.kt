package it.polito.wa2.g13.crmanalytics.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.convert.converter.Converter
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

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

                it.pathMatchers("/**").hasRole("ADMIN")
                it.anyExchange().denyAll()
            }
            .oauth2ResourceServer { it ->
                it.jwt { http ->
                    http.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
                }
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .build()
    }

    /**
     * Get roles from the jwt
     */

    fun grantedAuthoritiesExtractor(): Converter<Jwt, Mono<AbstractAuthenticationToken>> {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(GrantedAuthoritiesExtractor())
        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
    }

    internal class GrantedAuthoritiesExtractor : Converter<Jwt, Collection<GrantedAuthority>> {
        override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
            val realmAccess = jwt.getClaim<Map<String, Collection<String>>>("realm_access")

            return realmAccess
                ?.getOrDefault("roles", listOf())
                ?.map { SimpleGrantedAuthority("ROLE_$it") }
                ?: listOf()
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
