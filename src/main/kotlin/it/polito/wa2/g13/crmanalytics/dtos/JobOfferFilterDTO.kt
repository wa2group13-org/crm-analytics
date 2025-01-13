package it.polito.wa2.g13.crmanalytics.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class JobOfferFilterDTO(
    val op: Operation,
    val group: Group,
    @JsonFormat
    val base: ZonedDateTime = ZonedDateTime.now().minusYears(1),
) {
    enum class Group {
        Day,
        Month,
        Year;

        fun format() = when (this) {
            Day -> "%Y-%m-%d"
            Month -> "%Y-%m"
            Year -> "%Y"
        }
    }
}
