package kwduo.post.schema

import kwduo.member.schema.AuthorSchema
import java.time.LocalDateTime

data class PostDetailSchema(
    val id: Long,
    val postType: String,
    val projectType: String,
    val title: String,
    val department: String?,
    val `class`: String?,
    val wantedPosition: List<String>,
    val interestingField: List<String>,
    val recruitNumber: Int?,
    val author: AuthorSchema,
    val bookmark: BookmarkSchema,
    val techStack: List<String>,
    val createdAt: LocalDateTime,
)
