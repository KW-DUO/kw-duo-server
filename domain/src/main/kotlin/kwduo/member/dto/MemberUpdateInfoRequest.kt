package kwduo.member.dto

data class MemberUpdateInfoRequest(
    val nickname: String,
    val bio: String,
    val techStack: List<String>,
    val position: String,
    val githubUrl: String?,
    val baekjoonId: String?,
)
