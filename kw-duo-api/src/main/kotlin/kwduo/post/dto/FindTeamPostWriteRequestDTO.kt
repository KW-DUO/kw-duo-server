package kwduo.post.dto

data class FindTeamPostWriteRequestDTO(
    val projectType: String,
    val department: String,
    val className: String?,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
    val title: String,
    val content: String,
)
