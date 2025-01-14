package it.polito.wa2.g13.crmanalytics.data

import it.polito.wa2.g13.crmanalytics.dtos.ContactDTO
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Contact(
    val id: ObjectId,
    val value: DebeziumPayloadDTO<ContactDTO>,
) {
    companion object {
        fun from(dto: DebeziumPayloadDTO<ContactDTO>) = Contact(
            id = ObjectId(),
            value = dto,
        )
    }
}
