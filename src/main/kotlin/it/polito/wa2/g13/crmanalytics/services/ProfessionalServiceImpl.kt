package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Professional
import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.repositories.ProfessionalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProfessionalServiceImpl(
    private val professionalRepository: ProfessionalRepository
) : ProfessionalService {
    companion object {
        private val logger = LoggerFactory.getLogger(ProfessionalServiceImpl::class.java)
    }

    override suspend fun create(dto: DebeziumPayloadDTO<ProfessionalDTO>): Professional {
        val newProfessional = professionalRepository.save(Professional.from(dto)).awaitSingleOrNull()!!
        logger.info("Created ${newProfessional::class.qualifiedName}@${newProfessional.id}")
        return newProfessional
    }

    override suspend fun count(operation: Operation): Long {
        return professionalRepository.countByValueOp(operation).awaitSingle()
    }

    override fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return professionalRepository.countByFilter(filter.base, filter.op, filter.group)
            .asFlow()
    }
}