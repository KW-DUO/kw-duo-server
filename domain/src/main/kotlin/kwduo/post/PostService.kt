package kwduo.post

import kwduo.bookmark.BookmarkService
import kwduo.member.MemberService
import kwduo.post.dto.*
import kwduo.post.exception.PostNotFoundException
import org.springframework.stereotype.Service

@Service
class PostService(
    private val memberService: MemberService,
    private val postRepository: PostRepository,
    private val bookmarkService: BookmarkService,
) {
    fun writeFindTeammatePost(request: FindTeammatePostWriteRequest): FindTeammatePost {
        val post = request.toPost()
        return postRepository.saveFindTeammatePost(post)
    }

    fun writeFindTeamPost(request: FindTeamPostWriteRequest): FindTeamPost {
        val post = request.toPost()
        return postRepository.saveFindTeamPost(post)
    }

    fun updatePostDetail(
        requestMemberId: Long,
        postId: Long,
        request: FindTeamPostEditRequest,
    ) {
        val post =
            postRepository.findFindTeamPostById(postId)
                ?: throw PostNotFoundException()

        val isUpdated = post.update(requestMemberId, request)

        if (isUpdated) {
            postRepository.saveFindTeamPost(post)
        }
    }

    fun updatePostDetail(
        requestMemberId: Long,
        postId: Long,
        request: FindTeammatePostEditRequest,
    ) {
        val post =
            postRepository.findFindTeammatePostById(postId)
                ?: throw PostNotFoundException()

        val isUpdated = post.update(requestMemberId, request)

        if (isUpdated) {
            postRepository.saveFindTeammatePost(post)
        }
    }

    fun closePost(
        requestMemberId: Long,
        postId: Long,
    ) {
        val post =
            postRepository.findById(postId)
                ?: throw PostNotFoundException()

        post.close(requestMemberId)
        postRepository.save(post)
    }

    fun unClosePost(
        requestMemberId: Long,
        postId: Long,
    ) {
        val post =
            postRepository.findById(postId)
                ?: throw PostNotFoundException()

        post.unClose(requestMemberId)
        postRepository.save(post)
    }

    fun deletePost(
        requestMemberId: Long,
        postId: Long,
    ) {
        val post =
            postRepository.findById(postId)
                ?: throw PostNotFoundException()

        post.delete(requestMemberId)
        postRepository.save(post)
    }

    fun getPostDetail(
        requestMemberId: Long?,
        postId: Long,
    ): PostDetailInfo {
        val post =
            postRepository.findById(postId)
                ?: throw PostNotFoundException()

        val author = memberService.getMemberInfo(post.authorId)

        return PostDetailInfo.of(post, author, isBookmarked(requestMemberId, post))
    }

    private fun isBookmarked(
        requestMemberId: Long?,
        post: Post,
    ): Boolean {
        return requestMemberId?.let { bookmarkService.isBookmarked(it, post.id!!) } ?: false
    }
}
