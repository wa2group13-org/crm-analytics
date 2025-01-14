package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Message
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.MessageDTO

interface MessageService: GenericStatService {
    suspend fun create(dto: DebeziumPayloadDTO<MessageDTO>): Message
}