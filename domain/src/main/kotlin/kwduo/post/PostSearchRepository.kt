package kwduo.post

import kwduo.post.dto.PostSearchRequest
import kwduo.util.Page

interface PostSearchRepository {
    fun searchFindTeammatePost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long>

    fun searchFindTeamPost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long>
}
