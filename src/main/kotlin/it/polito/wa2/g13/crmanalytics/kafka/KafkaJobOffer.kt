package it.polito.wa2.g13.crmanalytics.kafka

import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.services.*
import jakarta.validation.Valid
import kotlinx.coroutines.runBlocking
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaJobOffer(
    private val jobOfferService: JobOfferService,
    private val messageService: MessageService,
    private val professionalService: ProfessionalService,
    private val contactService: ContactService,
    private val customerService: CustomerService,
) {
//    companion object {
//        private val logger = LoggerFactory.getLogger(KafkaJobOffer::class.java)
//    }
//
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
        runBlocking {
            jobOfferService.create(jobOffer.payload)
        }
    }

    @KafkaListener(
        id = "analytics-message",
        topics = ["\${kafka-config.message-topic}"],
        properties = ["spring.json.value.default.type:it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage"]
    )
    fun getMessage(@Payload @Valid payload: DebeziumMessage<MessageDTO>) {
        runBlocking {
            messageService.create(payload.payload)
        }
    }

    @KafkaListener(
        id = "analytics-professional",
        topics = ["\${kafka-config.professional-topic}"],
        properties = ["spring.json.value.default.type:it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage"]
    )
    fun getProfessional(@Payload @Valid payload: DebeziumMessage<ProfessionalDTO>) {
        runBlocking {
            professionalService.create(payload.payload)
        }
    }

    @KafkaListener(
        id = "analytics-contact",
        topics = ["\${kafka-config.contact-topic}"],
        properties = ["spring.json.value.default.type:it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage"]
    )
    fun getContact(@Payload @Valid payload: DebeziumMessage<ContactDTO>) {
        runBlocking {
            contactService.create(payload.payload)
        }
    }

    @KafkaListener(
        id = "analytics-customer",
        topics = ["\${kafka-config.customer-topic}"],
        properties = ["spring.json.value.default.type:it.polito.wa2.g13.crmanalytics.dtos.DebeziumMessage"]
    )
    fun getCustomer(@Payload @Valid payload: DebeziumMessage<CustomerDTO>) {
        runBlocking {
            customerService.create(payload.payload)
        }
    }
}