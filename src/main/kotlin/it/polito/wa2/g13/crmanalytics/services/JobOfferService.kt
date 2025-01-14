package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferDTO

interface JobOfferService: GenericStatService {
    suspend fun create(jobOffer: DebeziumPayloadDTO<JobOfferDTO>): JobOffer
}