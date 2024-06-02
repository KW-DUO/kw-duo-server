package kwduo.statistics

data class StatisticsResponse(
    val statistics: List<Statistics>,
) {
    data class Statistics(
        val value: String,
        val count: Long,
    )
}
