package it.polito.wa2.g13.crmanalytics.dtos

data class ProfessionalDTO(
    val id: Long,
    val dailyRate: Double,
    val employmentState: EmploymentState,
    val notes: String?,
    val contactId: Long,
) {
    enum class EmploymentState {
        Employed,
        Available,
        NotAvailable,
    }
}
