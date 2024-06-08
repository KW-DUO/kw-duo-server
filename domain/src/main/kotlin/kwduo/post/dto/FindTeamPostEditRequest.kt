package kwduo.post.dto

data class FindTeamPostEditRequest(
    val title: String,
    val content: String,
    val projectType: String,
    val className: String?,
    val department: String?,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
)
