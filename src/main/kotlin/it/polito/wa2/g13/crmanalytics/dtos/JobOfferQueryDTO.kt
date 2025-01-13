package it.polito.wa2.g13.crmanalytics.dtos

import org.springframework.data.annotation.Id

data class JobOfferQueryDTO(
    @Id
    val id: String,
    val count: Long,
)