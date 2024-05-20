package kwduo.member

import kwduo.member.dto.MemberUpdateInfoRequest
import java.time.LocalDateTime

class Member(
    val id: Long? = null,
    val oAuthId: String,
    var profileImgId: Long?,
    var nickname: String,
    var bio: String,
    val department: Department,
    var techStack: List<TechStack>,
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

    fun updateInfo(request: MemberUpdateInfoRequest) {
        this.profileImgId = request.profileImgId
        this.nickname = request.nickname
        this.bio = request.bio
        this.techStack = request.techStack.map { TechStack.of(it) }
        this.position = Position.of(request.position)
        this.githubUrl = request.githubUrl

        if (request.baekjoonId != null) {
            this.baekjoonInfo = BaekJoonInfo(request.baekjoonId)
        } else {
            removeBaekjoonInfo()
        }
    }

    fun hasBaekjoonInfo(): Boolean {
        return baekjoonInfo?.baekjoonId != null
    }

    fun updateBaekjoonTier(tier: BaekjoonTier) {
        check(hasBaekjoonInfo()) { "백준 정보가 없으면 티어를 갱신할 수 없습니다." }

        val prevInfo = baekjoonInfo
        this.baekjoonInfo = BaekJoonInfo(prevInfo!!.baekjoonId, tier)
    }

    fun removeBaekjoonInfo() {
        baekjoonInfo = null
    }
}
