package kwduo.member.dto

import kwduo.member.BaekJoonInfo
import kwduo.member.Department
import kwduo.member.KwEmailInfo
import kwduo.member.Member
import kwduo.member.Position
import kwduo.member.TechStack

data class MemberJoinRequest(
    val oAuthId: String,
    val nickname: String,
    val department: String,
    val techStack: List<String>,
    var position: String,
    val codingTestLanguage: String,
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
            nickname = nickname,
            bio = "",
            department = Department.of(department),
            techStack = techStack.map { TechStack.of(it) },
            position = Position.of(position),
            codingTestLanguage = codingTestLanguage,
            emailInfo = KwEmailInfo(email),
            githubUrl = githubUrl,
            baekjoonInfo = baekjoonInfo,
        )
    }
}
