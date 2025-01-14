package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.data.Professional
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ProfessionalRepository: ReactiveMongoRepository<Professional, ObjectId>, GenericStatRepository