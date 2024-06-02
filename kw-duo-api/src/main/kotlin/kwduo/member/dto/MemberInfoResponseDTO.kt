package kwduo.member.dto

data class MemberInfoResponseDTO(
    val id: Long,
    val nickname: String,
    val department: String,
    val position: String,
    val bio: String,
    val techStack: List<String>,
    val githubUrl: String?,
    val baekjoonId: String?,
) {
    constructor(memberInfo: MemberInfo) : this(
        id = memberInfo.id,
        nickname = memberInfo.nickname,
        department = memberInfo.department.name,
        position = memberInfo.position.name,
        bio = memberInfo.bio,
        techStack = memberInfo.techStack.map { it.name },
        githubUrl = memberInfo.githubUrl,
        baekjoonId = memberInfo.baekjoonId,
    )
}
