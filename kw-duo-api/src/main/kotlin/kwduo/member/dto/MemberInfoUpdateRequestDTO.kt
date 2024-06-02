package kwduo.member.dto

data class MemberInfoUpdateRequestDTO(
    val nickname: String,
    val department: String,
    val position: String,
    val bio: String,
    val techStack: List<String>,
    val githubUrl: String?,
    val baekjoonId: String?,
)
