package kwduo.post.dto

import kwduo.member.TechStack
import kwduo.member.dto.MemberInfo
import kwduo.post.FindTeamPost
import kwduo.post.FindTeammatePost
import kwduo.post.Post
import kwduo.post.ProjectType
import java.time.LocalDateTime

data class PostDetailInfo(
    val id: Long,
    val postType: String,
    val projectType: ProjectType,
    val title: String,
    val content: String,
    val department: String?,
    val className: String?,
    val wantedPosition: List<String>,
    val interestingField: List<String>,
    val recruitNumber: Int?,
    val author: AuthorInfo,
    val isBookmarked: Boolean,
    val techStack: List<TechStack>,
    val createdAt: LocalDateTime,
) {
    constructor(post: FindTeamPost, author: MemberInfo, isBookmarked: Boolean) : this(
        id = post.id!!,
        postType = "FIND_TEAM",
        projectType = post.projectType,
        title = post.title,
        content = post.content,
        department = post.department.displayName,
        className = post.className,
        wantedPosition = post.wantedPosition.map { it.value },
        interestingField = post.interestingField.map { it.value },
        recruitNumber = null,
        author = AuthorInfo(author.id, author.nickname, author.profileImgUrl),
        isBookmarked = isBookmarked,
        techStack = post.techStack,
        createdAt = post.createdAt,
    )

    constructor(post: FindTeammatePost, author: MemberInfo, isBookmarked: Boolean) : this(
        id = post.id!!,
        postType = "FIND_TEAMMATE",
        projectType = post.projectType,
        title = post.title,
        content = post.content,
        department = post.department.displayName,
        className = post.className,
        wantedPosition = post.wantedPosition.map { it.value },
        interestingField = post.interestingField.map { it.value },
        recruitNumber = post.recruitNumber,
        author = AuthorInfo(author.id, author.nickname, author.profileImgUrl),
        isBookmarked = isBookmarked,
        techStack = post.techStack,
        createdAt = post.createdAt,
    )

    companion object {
        fun of(
            post: Post,
            author: MemberInfo,
            isBookmarked: Boolean,
        ): PostDetailInfo {
            return when (post) {
                is FindTeamPost -> PostDetailInfo(post, author, isBookmarked)
                is FindTeammatePost -> PostDetailInfo(post, author, isBookmarked)
                else -> throw IllegalArgumentException("Invalid post type")
            }
        }
    }
}
