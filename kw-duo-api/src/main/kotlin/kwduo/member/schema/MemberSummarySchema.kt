package kwduo.member.schema

data class MemberSummarySchema(
    val id: Long,
    val nickname: String,
    val profileImgUrl: String?,
    val department: String,
    val techStack: List<String>,
)
