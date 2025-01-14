package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Professional
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO
import it.polito.wa2.g13.crmanalytics.dtos.ProfessionalDTO

interface ProfessionalService : GenericStatService {
    suspend fun create(dto: DebeziumPayloadDTO<ProfessionalDTO>): Professional
}