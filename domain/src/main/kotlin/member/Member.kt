package member

import java.time.LocalDateTime

class Member(
    val id: Long? = null,
    val oAuthId: String,
    val profileImgId: Long?,
    var nickname: String,
    var bio: String,
    val department: Department,
    private var techStack: List<TechStack>,
    var position: Position,
    private var emailInfo: KwEmailInfo,
    var githubUrl: String?,
    var baekjoonInfo: BaekJoonInfo?,
    val joinAt: LocalDateTime = LocalDateTime.now(),
) {
    val email
        get() = emailInfo.email
    val techStackNames: List<String>
        get() = techStack.map { it.displayName }

    val isEmailAuthenticated: Boolean
        get() = emailInfo.isAuthenticated()

    val baekjoonId
        get() = baekjoonInfo?.baekjoonId

    fun authenticateEmail() {
        emailInfo.authenticate()
    }

    fun hasBaekjoonInfo(): Boolean {
        return baekjoonInfo?.baekjoonId != null
    }

    fun updateBaekjoonInfo(
        baekjoonId: String,
        tier: BaekjoonTier,
        tierUpdatedAt: LocalDateTime = LocalDateTime.now()
    ) {
        baekjoonInfo = BaekJoonInfo(baekjoonId, tier, tierUpdatedAt)
    }

    fun updateBaekjoonTier(tier: BaekjoonTier) {
        val prevInfo = baekjoonInfo
        checkNotNull(prevInfo) { "백준 정보가 없으면 티어를 갱신할 수 없습니다." }

        updateBaekjoonInfo(prevInfo.baekjoonId, tier)
    }

    fun removeBaekjoonInfo() {
        baekjoonInfo = null
    }
}
