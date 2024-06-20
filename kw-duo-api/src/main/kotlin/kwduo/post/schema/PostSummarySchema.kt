package kwduo.post.schema

import kwduo.member.schema.AuthorSchema
import kwduo.post.dto.PostSummary
import java.time.LocalDateTime

data class PostSummarySchema(
    val id: Long,
    val postType: String,
    val projectType: String,
    val title: String,
    val department: String?,
    val className: String?,
    val wantedPosition: List<String>,
    val wantedField: List<String>,
    val author: AuthorSchema,
    val bookmark: BookmarkSchema,
    val techStack: List<String>,
    val createdAt: LocalDateTime,
) {
    constructor(post: PostSummary) : this(
        id = post.id,
        postType = post.postType,
        projectType = post.projectType.value,
        title = post.title,
        department = post.department?.name,
        className = post.className,
        wantedPosition = post.wantedPosition.map { it.value },
        wantedField = post.wantedField.map { it.value },
        author = AuthorSchema(post.author.id, post.author.nickname),
        bookmark = BookmarkSchema(post.isBookmarked),
        techStack = post.techStack.map { it.value },
        createdAt = post.createdAt,
    )
}
