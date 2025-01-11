package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferFilterDTO
import it.polito.wa2.g13.crmanalytics.repositories.JobOfferRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JobOfferServiceImpl(
    private val jobOfferRepository: JobOfferRepository,
) : JobOfferService {
    companion object {
        private val logger = LoggerFactory.getLogger(JobOfferServiceImpl::class.java)
    }

    override suspend fun create(jobOffer: DebeziumPayloadDTO<JobOfferDTO>): JobOffer {
        val newJob = jobOfferRepository.insert(JobOffer.from(jobOffer)).awaitSingle()
        logger.info("Created ${newJob::class.simpleName}@${newJob.id}")
        return newJob
    }

    override suspend fun count(filter: JobOfferFilterDTO): Long {
        return jobOfferRepository.countByValueOp(filter.op).asFlow().first()
    }
}