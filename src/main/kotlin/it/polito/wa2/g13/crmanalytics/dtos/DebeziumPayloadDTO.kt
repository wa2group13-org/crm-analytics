package it.polito.wa2.g13.crmanalytics.dtos

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.Valid

enum class Operation(private val value: String) {
    @JsonProperty("c")
    C("c"),

    @JsonProperty("u")
    U("u"),

    @JsonProperty("d")
    D("d");

    companion object {
        @JsonCreator
        @JvmStatic
        fun from(value: String): Operation {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("Invalid value: $value")
        }
    }
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
