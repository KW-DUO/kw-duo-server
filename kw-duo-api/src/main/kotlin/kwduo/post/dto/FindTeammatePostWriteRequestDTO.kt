package kwduo.post.dto

data class FindTeammatePostWriteRequestDTO(
    val projectType: String,
    val department: String?,
    val className: String?,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
    val recruitNumber: Int,
    val title: String,
    val content: String,
)
