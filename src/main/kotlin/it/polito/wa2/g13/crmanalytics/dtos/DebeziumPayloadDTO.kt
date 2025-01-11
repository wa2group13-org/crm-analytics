package it.polito.wa2.g13.crmanalytics.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.Valid

enum class Operation {
    @JsonProperty("c")
    C,
    @JsonProperty("u")
    U,
    @JsonProperty("d")
    D,
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class DebeziumPayloadDTO<T>(
    @param:Valid
    val before: T?,
    @param:Valid
    val after: T?,
    @param:Valid
    val source: Map<String, Any?>,
    val transaction: String?,
    val op: Operation,
    val tsMs: Long,
    val tsUs: Long,
    val tsNs: Long,
)
