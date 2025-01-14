package it.polito.wa2.g13.crmanalytics.controllers

import it.polito.wa2.g13.crmanalytics.dtos.GenericFilterDTO
import it.polito.wa2.g13.crmanalytics.dtos.GenericStatDTO
import it.polito.wa2.g13.crmanalytics.dtos.Operation
import it.polito.wa2.g13.crmanalytics.services.CustomerService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/API/customers")
class CustomerController(
    private val customerService: CustomerService,
) {

    @GetMapping("/all")
    suspend fun getAllByOperation(@RequestParam operation: Operation): Long {
        return customerService.count(operation)
    }

    @GetMapping("/filter")
    suspend fun getAllByFilter(@Valid filter: GenericFilterDTO): Flow<GenericStatDTO> {
        return customerService.countBy(filter)
    }
}