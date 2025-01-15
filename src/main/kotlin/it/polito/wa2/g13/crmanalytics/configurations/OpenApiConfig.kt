package it.polito.wa2.g13.crmanalytics.configurations

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import it.polito.wa2.g13.crmanalytics.properties.OpenapiConfigProperties
import it.polito.wa2.g13.crmanalytics.properties.ProjectConfigProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles

@Configuration
class OpenApiConfig(
    private val projectConfigProperties: ProjectConfigProperties,
    private val environment: Environment,
    private val openapiConfigProperties: OpenapiConfigProperties,
) {
    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("CRM Analytics API Documentation")
                .version(projectConfigProperties.version)
        ).apply {
            if (environment.acceptsProfiles(Profiles.of("api-docs"))) {
                this.servers(listOf(Server().url(openapiConfigProperties.baseUrl)))
            }
        }
}
