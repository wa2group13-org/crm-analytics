package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Message
import it.polito.wa2.g13.crmanalytics.dtos.*
import it.polito.wa2.g13.crmanalytics.repositories.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
) : MessageService {
    companion object {
        private val logger = LoggerFactory.getLogger(MessageServiceImpl::class.java)
    }

    override suspend fun create(dto: DebeziumPayloadDTO<MessageDTO>): Message {
        val newMessage = messageRepository.save(Message.from(dto)).awaitSingleOrNull()!!
        logger.info("Created ${newMessage::class.qualifiedName}@${newMessage.id}")
        return newMessage
    }

    override suspend fun count(operation: Operation): Long {
        return messageRepository.countByValueOp(operation).awaitSingle()
    }

    override fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return messageRepository.countByFilter(filter.base, filter.op, filter.group)
            .asFlow()
    }
}