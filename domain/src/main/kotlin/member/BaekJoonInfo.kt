package member

import java.time.LocalDateTime

data class BaekJoonInfo(
    val baekjoonId: String,
    val tier: BaekjoonTier?,
    val tierUpdatedAt: LocalDateTime = LocalDateTime.now(),
)
