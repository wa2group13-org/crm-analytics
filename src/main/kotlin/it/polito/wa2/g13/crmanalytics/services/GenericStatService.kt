package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.dtos.GenericFilterDTO
import it.polito.wa2.g13.crmanalytics.dtos.GenericStatDTO
import it.polito.wa2.g13.crmanalytics.dtos.Operation
import kotlinx.coroutines.flow.Flow

interface GenericStatService {
    suspend fun count(operation: Operation): Long

    fun countBy(filter: GenericFilterDTO): Flow<GenericStatDTO>
}