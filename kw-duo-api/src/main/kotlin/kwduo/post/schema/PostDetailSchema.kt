package kwduo.post.schema

import kwduo.member.schema.AuthorSchema
import kwduo.post.dto.PostDetailInfo
import java.time.LocalDateTime

data class PostDetailSchema(
    val id: Long,
    val postType: String,
    val projectType: String,
    val title: String,
    val content: String,
    val department: String?,
    val className: String?,
    val wantedPosition: List<String>,
    val interestingField: List<String>,
    val recruitNumber: Int?,
    val author: AuthorSchema,
    val bookmark: BookmarkSchema,
    val techStack: List<String>,
    val createdAt: LocalDateTime,
) {
    constructor(postDetail: PostDetailInfo) : this(
        id = postDetail.id,
        postType = postDetail.postType,
        projectType = postDetail.projectType.value,
        title = postDetail.title,
        content = postDetail.content,
        department = postDetail.department,
        className = postDetail.className,
        wantedPosition = postDetail.wantedPosition,
        interestingField = postDetail.interestingField,
        recruitNumber = postDetail.recruitNumber,
        author =
            AuthorSchema(
                postDetail.author.id,
                postDetail.author.nickname,
            ),
        bookmark = BookmarkSchema(postDetail.isBookmarked),
        techStack = postDetail.techStack.map { it.value },
        createdAt = postDetail.createdAt,
    )
}
