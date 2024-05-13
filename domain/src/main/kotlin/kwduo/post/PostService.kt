package kwduo.post

import kwduo.member.Member
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
}
