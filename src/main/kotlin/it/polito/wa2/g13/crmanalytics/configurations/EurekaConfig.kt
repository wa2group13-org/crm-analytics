package it.polito.wa2.g13.crmanalytics.configurations

import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("prod")
@Configuration
@EnableDiscoveryClient
class EurekaConfig