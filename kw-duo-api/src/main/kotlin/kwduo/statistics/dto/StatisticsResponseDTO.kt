package kwduo.statistics.dto

data class StatisticsResponseDTO(
    val statistics: List<Info>,
) {
    data class Info(
        val value: String,
        val count: Long,
    )
}
