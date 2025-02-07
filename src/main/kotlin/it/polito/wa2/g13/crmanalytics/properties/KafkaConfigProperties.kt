package it.polito.wa2.g13.crmanalytics.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * These properties are used to configure the topics to receive
 * information on the kafka replicas.
 */
@ConfigurationProperties("kafka-config")
class KafkaConfigProperties {
    /**
     * Topic of job_offer table
     */
    lateinit var jobOfferTopic: String
//    lateinit var jobOfferSkillsTopic: String
    lateinit var contactTopic: String
    lateinit var messageTopic: String
    lateinit var professionalTopic: String
    lateinit var customerTopic: String
}
