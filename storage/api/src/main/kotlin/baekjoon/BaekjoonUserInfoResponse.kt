package baekjoon

import java.io.Serializable
import java.time.LocalDateTime

data class BaekjoonUserInfoResponse(
    val blocked: Boolean,
    val reverseBlocked: Boolean,
    val isRival: Boolean,
    val isReverseRival: Boolean,
    val handle: String,
    val bio: String,
    val badgeId: String,
    val backgroundId: String,
    val profileImageUrl: String,
    val solvedCount: Int,
    val voteCount: Int,
    val `class`: Int,
    val classDecoration: String,
    val rivalCount: Int,
    val reverseRivalCount: Int,
    val tier: Int,
    val rating: Int,
    val ratingByProblemsSum: Int,
    val ratingByClass: Int,
    val ratingBySolvedCount: Int,
    val ratingByVoteCount: Int,
    val arenaTier: Int,
    val arenaRating: Int,
    val arenaMaxTier: Int,
    val arenaMaxRating: Int,
    val arenaCompletedRoundCount: Int,
    val maxStreak: Int,
    val coins: Int,
    val stardusts: Int,
    val bannedUntil: LocalDateTime,
    val proUntil: LocalDateTime,
    val rank: Int,
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
