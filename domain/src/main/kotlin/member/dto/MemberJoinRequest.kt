package member.dto

import member.BaekJoonInfo
import member.Department
import member.KwEmailInfo
import member.Member
import member.Position
import member.TechStack

data class MemberJoinRequest(
    val oAuthId: String,
    val profileImgId: Long?,
    val nickname: String,
    val department: String,
    val techStack: List<String>,
    var position: String,
    val email: String,
    var githubUrl: String?,
    var baekjoonId: String?,
) {
    fun toMember(): Member {
        val baekjoonInfo =
            when (baekjoonId) {
                null -> null
                else -> BaekJoonInfo(baekjoonId!!, null)
            }

        return Member(
            oAuthId = oAuthId,
            profileImgId = profileImgId,
            nickname = nickname,
            bio = "",
            department = Department.of(department),
            techStack = techStack.map { TechStack.of(it) },
            position = Position.of(position),
            emailInfo = KwEmailInfo(email),
            githubUrl = githubUrl,
            baekjoonInfo = baekjoonInfo,
        )
    }
}
