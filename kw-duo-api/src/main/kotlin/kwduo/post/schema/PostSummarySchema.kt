package kwduo.post.schema

import kwduo.member.Member
import kwduo.member.schema.AuthorSchema
import kwduo.post.FindTeammatePost
import kwduo.post.Post
import java.time.LocalDateTime

data class PostSummarySchema(
    val id: Long,
    val postType: String,
    val projectType: String,
    val title: String,
    val department: String,
    val className: String?,
    val wantedPosition: List<String>,
    val wantedField: List<String>,
    val author: AuthorSchema,
    val bookmark: BookmarkSchema,
    val techStack: List<String>,
    val createdAt: LocalDateTime,
) {
    constructor(
        post: Post,
        author: Member,
        className: String?,
        department: String,
        profileImgUrl: String?,
        isBookmarked: Boolean,
    ) : this(
        id = post.id!!,
        postType =
            when (post) {
                is FindTeammatePost -> "FIND_TEAMMATE"
                else -> "FIND_TEAM"
            },
        projectType = post.projectType.value,
        title = post.title,
        department = department,
        className = className,
        wantedPosition = post.wantedPosition.map { it.displayName },
        wantedField = post.interestingField.map { it.value },
        author =
            AuthorSchema(
                id = author.id!!,
                profileImgUrl = profileImgUrl,
                nickname = author.nickname,
            ),
        bookmark = BookmarkSchema(isBookmarked),
        techStack = post.techStack.map { it.value },
        createdAt = post.createdAt,
    )
}
