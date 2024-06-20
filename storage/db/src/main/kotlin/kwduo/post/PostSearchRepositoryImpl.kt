package kwduo.post

import kwduo.post.dto.PostSearchRequest
import kwduo.util.Page
import org.springframework.stereotype.Component

@Component
class PostSearchRepositoryImpl(
    private val postSearchQueryDSLRepository: PostSearchQueryDSLRepository,
) : PostSearchRepository {
    override fun searchFindTeammatePost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long> {
        return postSearchQueryDSLRepository.searchFindTeammatePost(requestMemberId, request)
    }

    override fun searchFindTeamPost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long> {
        return postSearchQueryDSLRepository.searchFindTeamPost(requestMemberId, request)
    }
}
