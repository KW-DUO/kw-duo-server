package kwduo.post.dto

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack
import kwduo.post.Field
import kwduo.post.ProjectType
import java.time.LocalDateTime

data class PostSummary(
    val id: Long,
    val postType: String,
    val projectType: ProjectType,
    val title: String,
    val department: Department?,
    val className: String?,
    val wantedPosition: List<Position>,
    val wantedField: List<Field>,
    val author: Author,
    val isBookmarked: Boolean,
    val techStack: List<TechStack>,
    val createdAt: LocalDateTime,
) {
    data class Author(
        val id: Long,
        val nickname: String,
    )
}
