package kwduo.statistics

import kwduo.statistics.dto.StatisticsResponseDTO
import org.springframework.stereotype.Service

@Service
class StatisticsService(
    private val statisticsRepository: StatisticsRepository,
) {
    fun getAllMemberCount(): Long {
        return statisticsRepository.getAllMemberCount()
    }

    fun getPositionStatistics(): StatisticsResponseDTO {
        return mapToResponse(statisticsRepository.getPositionStatistics())
    }

    fun getCodingTestStatistics(): StatisticsResponseDTO {
        return mapToResponse(statisticsRepository.getCodingTestStatistics())
    }

    fun getTechStackStatistics(): StatisticsResponseDTO {
        return mapToResponse(statisticsRepository.getTechStackStatistics())
    }

    private fun mapToResponse(statisticsResponse: StatisticsResponse) =
        StatisticsResponseDTO(
            statisticsResponse.statistics.map { StatisticsResponseDTO.Info(it.value, it.count) },
        )
}
