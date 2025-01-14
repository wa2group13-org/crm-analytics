package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.data.Message
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MessageRepository : ReactiveMongoRepository<Message, String>, GenericStatRepository