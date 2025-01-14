package it.polito.wa2.g13.crmanalytics.data

import it.polito.wa2.g13.crmanalytics.dtos.CustomerDTO
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Customer(
    @Id
    val id: ObjectId,
    val value: DebeziumPayloadDTO<CustomerDTO>,
) {
    companion object {
        fun from(dto: DebeziumPayloadDTO<CustomerDTO>) = Customer(
            id = ObjectId(),
            value = dto,
        )
    }
}
