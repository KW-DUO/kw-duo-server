package kwduo.post

import kwduo.bookmark.BookmarkService
import kwduo.member.MemberReader
import kwduo.post.dto.PostSearchRequest
import kwduo.post.dto.PostSummary
import kwduo.util.Page
import org.springframework.stereotype.Service

@Service
class PostSearchService(
    private val postRepository: PostRepository,
    private val postSearchRepository: PostSearchRepository,
    private val memberReader: MemberReader,
    private val bookmarkService: BookmarkService,
) {
    fun searchFindTeammatePost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<PostSummary> {
        val postIds =
            postSearchRepository.searchFindTeammatePost(requestMemberId, request)

        return findPostSummary(requestMemberId, postIds)
    }

    fun searchFindTeamPost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<PostSummary> {
        val postIds =
            postSearchRepository.searchFindTeamPost(requestMemberId, request)

        return findPostSummary(requestMemberId, postIds)
    }

    private fun findPostSummary(
        requestMemberId: Long?,
        postIds: Page<Long>,
    ): Page<PostSummary> {
        return postIds.map {
            val post =
                postRepository.findById(it)
                    ?: throw IllegalArgumentException("Post not found: $it")

            val author = memberReader.findById(post.authorId)
            val isBookmarked = requestMemberId != null && bookmarkService.isBookmarked(requestMemberId, post.id!!)

            return@map PostSummary(
                id = post.id!!,
                postType = getPostType(post),
                projectType = post.projectType,
                title = post.title,
                department = post.department,
                className = post.className,
                wantedPosition = post.wantedPosition,
                wantedField = post.interestingField,
                author =
                    PostSummary.Author(
                        id = author.id!!,
                        nickname = author.nickname,
                    ),
                isBookmarked = isBookmarked,
                techStack = post.techStack,
                createdAt = post.createdAt,
            )
        }
    }

    private fun getPostType(post: Post) =
        when (post) {
            is FindTeammatePost -> "FIND-TEAMMATE"
            is FindTeamPost -> "FIND-TEAM"
            else -> throw IllegalArgumentException("Unknown post type")
        }
}
