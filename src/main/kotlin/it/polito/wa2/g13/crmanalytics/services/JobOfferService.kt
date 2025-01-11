package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferFilterDTO

interface JobOfferService {
    suspend fun create(jobOffer: DebeziumPayloadDTO<JobOfferDTO>): JobOffer

    suspend fun count(filter: JobOfferFilterDTO): Long
}