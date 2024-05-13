package kwduo.member

import java.time.LocalDateTime

data class BaekJoonInfo(
    val baekjoonId: String,
    val tier: BaekjoonTier? = null,
    val tierUpdatedAt: LocalDateTime = LocalDateTime.now(),
)
