package it.polito.wa2.g13.crmanalytics.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "openapi")
class OpenapiConfigProperties {
    /**
     * This will be string inserter in the `server` filed of the
     * openapi documentation.
     */
    lateinit var baseUrl: String
}