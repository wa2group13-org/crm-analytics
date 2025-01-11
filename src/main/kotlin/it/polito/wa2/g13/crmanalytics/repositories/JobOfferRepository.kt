package it.polito.wa2.g13.crmanalytics.repositories

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.Operation
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface JobOfferRepository: ReactiveMongoRepository<JobOffer, ObjectId> {
    fun countByValueOp(op: Operation): Mono<Long>
}