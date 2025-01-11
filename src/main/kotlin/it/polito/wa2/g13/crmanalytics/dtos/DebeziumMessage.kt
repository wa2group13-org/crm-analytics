package it.polito.wa2.g13.crmanalytics.dtos

import jakarta.validation.Valid

data class DebeziumMessage<T>(
    @param:Valid
    val schema: Map<String, Any?>,
    @param:Valid
    val payload: DebeziumPayloadDTO<T>
)
