package it.polito.wa2.g13.crmanalytics.kafka

import it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferDTO
import it.polito.wa2.g13.crmanalytics.services.JobOfferService
import jakarta.validation.Valid
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaJobOffer(
    private val jobOfferService: JobOfferService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(KafkaJobOffer::class.java)
    }

//    @Bean
//    fun consumerFactory(kafkaProps: KafkaProperties): DefaultKafkaConsumerFactory<String, DebeziumPayloadDTO<*>> {
//        val consumerConfig = HashMap(kafkaProps.consumer.properties)
//        val deserializer = JsonDeserializer(DebeziumPayloadDTO::class.java)
//        return DefaultKafkaConsumerFactory(, StringDeserializer(), deserializer)
//    }

    @KafkaListener(
        id = "analytics-job-offer",
        topics = ["\${kafka-config.job-offer-topic}"],
        properties = ["spring.json.value.default.type:it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage"]
    )
    fun getJobOffer(@Payload @Valid jobOffer: DebeziumMessage<JobOfferDTO>) {
        logger.info(jobOffer.toString())
        runBlocking {
            jobOfferService.create(jobOffer.payload)
        }
    }

//    @KafkaListener(id = "analytics-job-offer-skills", topics = ["\${kafka-config.job-offer-skills-topic}"])
//    suspend fun getJobOfferSkills(@Payload @Valid jobOfferSkill: DebeziumPayloadDTO<JobOfferSkillDTO>) {
//
//    }
}