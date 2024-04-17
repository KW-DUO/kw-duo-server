package kwduo.member.dto

import member.dto.MemberJoinRequest
import java.time.LocalDateTime

data class MemberJoinRequestDTO(
    val oAuthId: String,
    val profileImgId: Long?,
    val nickname: String,
    var bio: String,
    val department: String,
    val techStack: List<String>,
    var position: String,
    val email: String,
    var githubUrl: String?,
    var baekjoonId: String?,
    val joinAt: LocalDateTime,
) {
    fun toRequest(): MemberJoinRequest {
        return MemberJoinRequest(
            oAuthId = oAuthId,
            profileImgId = profileImgId,
            nickname = nickname,
            bio = bio,
            department = department,
            techStack = techStack,
            position = position,
            email = email,
            githubUrl = githubUrl,
            baekjoonId = baekjoonId,
            joinAt = joinAt,
        )
    }
}
