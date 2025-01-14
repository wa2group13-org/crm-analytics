package it.polito.wa2.g13.crmanalytics.dtos

data class ContactDTO(
    val id: Long,
    val name: String,
    val surname: String,
    val category: Category,
    val ssn: String?,
) {
    enum class Category {
        Customer,
        Professional,
        Unknown;
    }
}
