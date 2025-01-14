package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Contact
import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.repositories.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(
    private val contactRepository: ContactRepository,
) : ContactService {
    companion object {
        private val logger = LoggerFactory.getLogger(ContactServiceImpl::class.java)
    }

    override suspend fun create(dto: DebeziumPayloadDTO<ContactDTO>): Contact {
        val newContact = contactRepository.save(Contact.from(dto)).awaitSingleOrNull()!!
        logger.info("Created ${newContact::class.qualifiedName}@${newContact.id}")
        return newContact
    }

    override suspend fun count(operation: Operation): Long {
        return contactRepository.countByValueOp(operation).awaitSingle()
    }

    override fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return contactRepository
            .countByFilter(filter.base, filter.op, filter.group)
            .asFlow()
    }
}