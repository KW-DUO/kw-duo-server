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

        return findPostSummary(postIds)
    }

    fun searchFindTeamPost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<PostSummary> {
        val postIds =
            postSearchRepository.searchFindTeamPost(requestMemberId, request)

        return findPostSummary(postIds)
    }

    private fun findPostSummary(postIds: Page<Long>): Page<PostSummary> {
        return postIds.map {
            val post =
                postRepository.findById(it)
                    ?: throw IllegalArgumentException("Post not found: $it")

            val author = memberReader.findById(post.authorId)
            val isBookmarked = bookmarkService.isBookmarked(author.id!!, post.id!!)

            return@map PostSummary(
                id = post.id,
                postType =
                    when (post) {
                        is FindTeammatePost -> "find-teammate"
                        is FindTeamPost -> "find-team"
                        else -> throw IllegalArgumentException("Unknown post type")
                    },
                projectType = post.projectType,
                title = post.title,
                department = post.department,
                className = post.className,
                wantedPosition = post.wantedPosition,
                wantedField = post.interestingField,
                author =
                    PostSummary.Author(
                        id = author.id,
                        nickname = author.nickname,
                        profileImageUrl = null,
                    ),
                isBookmarked = isBookmarked,
                techStack = post.techStack,
                createdAt = post.createdAt,
            )
        }
    }
}
