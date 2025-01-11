package it.polito.wa2.g13.crmanalytics

import it.polito.wa2.g13.crmanalytics.properties.KafkaConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
	KafkaConfigProperties::class,
)
class CrmAnalyticsApplication

fun main(args: Array<String>) {
	runApplication<CrmAnalyticsApplication>(*args)
}
