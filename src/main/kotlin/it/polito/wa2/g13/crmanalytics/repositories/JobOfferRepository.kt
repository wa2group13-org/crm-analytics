package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface JobOfferRepository : ReactiveMongoRepository<JobOffer, ObjectId>, GenericStatRepository