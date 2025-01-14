package it.polito.wa2.g13.crmanalytics.data

import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.ProfessionalDTO
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Professional(
    @Id
    val id: ObjectId,
    val value: DebeziumPayloadDTO<ProfessionalDTO>,
) {
    companion object {
        fun from(dto: DebeziumPayloadDTO<ProfessionalDTO>) = Professional(
            id = ObjectId(),
            value = dto,
        )
    }
}
