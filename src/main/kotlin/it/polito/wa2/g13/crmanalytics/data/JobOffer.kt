package it.polito.wa2.g13.crmanalytics.data

import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferDTO
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class JobOffer(
    @Id
    val id: ObjectId,
    val value: DebeziumPayloadDTO<JobOfferDTO>
) {
    companion object {
        fun from(job: DebeziumPayloadDTO<JobOfferDTO>) = JobOffer(
            id = ObjectId(),
            value = job,
        )
    }
}
