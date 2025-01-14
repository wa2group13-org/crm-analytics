package it.polito.wa2.g13.crmanalytics.services

import it.polito.wa2.g13.crmanalytics.data.Customer
import it.polito.wa2.g13.crmanalytics.dtos.CustomerDTO
import it.polito.wa2.g13.crmanalytics.dtos.DebeziumPayloadDTO

interface CustomerService: GenericStatService {
    suspend fun create(dto: DebeziumPayloadDTO<CustomerDTO>): Customer
}