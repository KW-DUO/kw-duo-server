package kwduo.statistics

import kwduo.member.MemberJpaRepository
import kwduo.member.MemberTechStackJpaRepository
import org.springframework.stereotype.Repository

@Repository
class StatisticsRepository(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberTechStackJpaRepository: MemberTechStackJpaRepository,
) {
    fun getAllMemberCount(): Long {
        return memberJpaRepository.getAllCount()
    }

    fun getPositionStatistics(): StatisticsResponse {
        val members = memberJpaRepository.findAll()
        val position = members.groupBy { it.position }

        return StatisticsResponse(
            position.map { StatisticsResponse.Statistics(it.key.value, it.value.size.toLong()) },
        )
    }

    fun getCodingTestStatistics(): StatisticsResponse {
        val members = memberJpaRepository.findAll()
        val codingTest = members.groupBy { it.codingTestLanguage }

        return StatisticsResponse(
            codingTest.map { StatisticsResponse.Statistics(it.key, it.value.size.toLong()) },
        )
    }

    fun getTechStackStatistics(): StatisticsResponse {
        val techStacks = memberTechStackJpaRepository.findAll()
        val techStackStatistics = techStacks.groupBy { it.techStack }

        return StatisticsResponse(
            techStackStatistics.map { StatisticsResponse.Statistics(it.key.value, it.value.size.toLong()) },
        )
    }
}
