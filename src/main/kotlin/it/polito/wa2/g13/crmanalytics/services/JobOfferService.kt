package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.*
import kotlinx.coroutines.flow.Flow

interface JobOfferService {
    suspend fun create(jobOffer: DebeziumPayloadDTO<JobOfferDTO>): JobOffer

    suspend fun count(operation: Operation): Long

    fun countBy(filter: JobOfferFilterDTO): Flow<JobOfferQueryDTO>
}