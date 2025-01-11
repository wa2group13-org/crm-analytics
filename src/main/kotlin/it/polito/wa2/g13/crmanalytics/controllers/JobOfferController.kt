package it.polito.wa2.g13.crmanalytics.controllers

import it.polito.wa2.g13.crmanalytics.services.JobOfferService
import org.springframework.stereotype.Controller

@Controller
class JobOfferController(
    private val jobOfferService: JobOfferService,
) {

}