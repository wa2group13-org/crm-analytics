package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.JobOffer
import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.repositories.JobOfferRepository
import kotlinx.coroutines.flow.Flow
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
        logger.info("Created ${newJob::class.qualifiedName}@${newJob.id}")
        return newJob
    }

    override suspend fun count(operation: Operation): Long {
        return jobOfferRepository.countByValueOp(operation).awaitSingle()
    }

    override fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return jobOfferRepository
            .countByFilter(filter.base, filter.op, filter.group)
            .asFlow()
    }
}