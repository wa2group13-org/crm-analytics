package it.polito.wa2.g13.crmanalytics.controllers

import it.polito.wa2.g13.crmanalytics.dtos.JobOfferFilterDTO
import it.polito.wa2.g13.crmanalytics.dtos.JobOfferQueryDTO
import it.polito.wa2.g13.crmanalytics.dtos.Operation
import it.polito.wa2.g13.crmanalytics.services.JobOfferService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/API/jobs")
class JobOfferController(
    private val jobOfferService: JobOfferService,
) {
    @GetMapping("/all")
    suspend fun getAllByOperation(@RequestParam operation: Operation): Long {
        return jobOfferService.count(operation)
    }

    @GetMapping("/filter")
    suspend fun getAllByFilter(@Valid filter: JobOfferFilterDTO): Flow<JobOfferQueryDTO> {
        return jobOfferService.countBy(filter)
    }
}