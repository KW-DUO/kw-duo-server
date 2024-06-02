package kwduo.statistics

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.statistics.dto.AllUserCountResponseDTO
import kwduo.statistics.dto.StatisticsResponseDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Statistics", description = "통계 API")
@RestController
class StatisticsController(
    private val statisticsService: StatisticsService,
) {
    @Operation(summary = "포지션 통계 조회")
    @GetMapping("/statistics/position")
    fun getPositionStatistics(): StatisticsResponseDTO {
        return statisticsService.getPositionStatistics()
    }

    @Operation(summary = "코딩테스트 통계 조회")
    @GetMapping("/statistics/coding-test")
    fun getCodingTestStatistics(): StatisticsResponseDTO {
        return statisticsService.getCodingTestStatistics()
    }

    @Operation(summary = "기술 스택 통계 조회")
    @GetMapping("/statistics/tech-stack")
    fun getTechStackStatistics(): StatisticsResponseDTO {
        return statisticsService.getTechStackStatistics()
    }

    @Operation(summary = "전체 유저 수 조회")
    @GetMapping("/statistics/all-user-count")
    fun getAllUserCount(): AllUserCountResponseDTO {
        return AllUserCountResponseDTO(statisticsService.getAllMemberCount())
    }
}
