package post

import java.time.LocalDate

data class RecruitDeadLine(
    val hasDeadLine: Boolean,
    val deadLine: LocalDate?,
) {
    init {
        require(hasDeadLine || deadLine == null) { "마감 기한이 있는 경우 마감일을 입력해야 합니다." }
        require(deadLine == null || deadLine.isAfter(yesterday())) { "마감일은 오늘 이후여야 합니다." }
    }

    fun isRecruiting(now: LocalDate = LocalDate.now()): Boolean {
        return !hasDeadLine || !deadLine!!.isBefore(now)
    }

    private fun yesterday() = LocalDate.now().minusDays(1)
}
