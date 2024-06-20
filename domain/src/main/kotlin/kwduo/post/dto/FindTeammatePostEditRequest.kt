package kwduo.post.dto

data class FindTeammatePostEditRequest(
    val title: String,
    val content: String,
    val className: String?,
    val department: String?,
    val projectType: String,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
    val recruitNumber: Int,
)
