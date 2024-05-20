package kwduo.post

import kwduo.post.dto.FindTeamPostWriteRequest
import kwduo.post.dto.FindTeammatePostWriteRequest
import kwduo.post.exception.PostNotFoundException
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
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
        title: String,
        content: String,
    ) {
//        val post =
//            postRepository.findById(postId)
//                ?: throw PostNotFoundException()
//
//        val isUpdated = post.updateDetail(requestMemberId, title, content)
//
//        if (isUpdated) {
//            postRepository.save(post)
//        }
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
}
