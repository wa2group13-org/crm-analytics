package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Contact
import it.polito.wa2.g13.crmanalytics.dtos.ContactDTO
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO

interface ContactService : GenericStatService {
    suspend fun create(dto: DebeziumPayloadDTO<ContactDTO>): Contact
}