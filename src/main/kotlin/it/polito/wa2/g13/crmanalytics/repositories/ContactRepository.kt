package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.data.Contact
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ContactRepository: ReactiveMongoRepository<Contact, ObjectId>, GenericStatRepository