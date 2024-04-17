package kwduo.member.dto

data class MemberInfoResponseDTO(
    val id: Long,
    val profileImgUrl: String?,
    val profileImgId: Long?,
    val nickname: String,
    val department: String,
    val position: String,
    val bio: String,
    val techStack: List<String>,
    val githubUrl: String,
    val baekjoonId: String,
)
