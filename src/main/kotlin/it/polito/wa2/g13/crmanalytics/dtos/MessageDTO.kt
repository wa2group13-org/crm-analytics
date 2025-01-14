package it.polito.wa2.g13.crmanalytics.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class MessageDTO(
    val id: Long,
    val subject: String?,
    val body: String?,
    val sender: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val date: ZonedDateTime,
    val channel: String,
    val priority: Priority,
    val status: Status,
    val mailId: String?,
) {
    enum class Priority {
        Low,
        Medium,
        High;
    }

    enum class Status {
        Received,
        Read,
        Discarded,
        Processing,
        Done,
        Failed;
    }
}