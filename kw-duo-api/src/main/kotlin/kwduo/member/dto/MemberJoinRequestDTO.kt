package kwduo.member.dto

data class MemberJoinRequestDTO(
    val oAuthId: String,
    val nickname: String,
    val department: String,
    val techStack: List<String>,
    var position: String,
    val email: String,
    val githubUrl: String?,
    val baekjoonId: String?,
) {
    fun toRequest() =
        MemberJoinRequest(
            oAuthId = oAuthId,
            nickname = nickname,
            department = department,
            techStack = techStack,
            position = position,
            email = email,
            githubUrl = githubUrl,
            baekjoonId = baekjoonId,
        )
}
