package it.polito.wa2.g13.crmanalytics.data

import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.MessageDTO
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Message(
    @Id
    val id: ObjectId,
    val value: DebeziumPayloadDTO<MessageDTO>,
) {
    companion object {
        fun from(dto: DebeziumPayloadDTO<MessageDTO>) = Message(
            id = ObjectId(),
            value = dto,
        )
    }
}
