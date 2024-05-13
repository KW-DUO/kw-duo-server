package kwduo.post

import kwduo.member.Member
import kwduo.post.dto.FindTeamPostWriteRequest
import kwduo.post.dto.FindTeammatePostWriteRequest
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    fun updatePostDetail(
        requestMember: Member,
        post: Post,
        title: String,
        content: String,
    ) {
        val isUpdated = post.updateDetail(requestMember, title, content)

        if (isUpdated) {
            postRepository.save(post)
        }
    }

    fun writeFindTeammatePost(request: FindTeammatePostWriteRequest): FindTeammatePost {
        val post = request.toPost()
        return postRepository.saveFindTeammatePost(post)
    }

    fun writeFindTeamPost(request: FindTeamPostWriteRequest): FindTeamPost {
        val post = request.toPost()
        return postRepository.saveFindTeamPost(post)
    }
}
