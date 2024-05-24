package kwduo.post

import kwduo.post.dto.PostSummary
import kwduo.util.Page
import org.springframework.stereotype.Repository

@Repository
class PostSearchRepositoryImpl : PostSearchRepository {
    override fun searchFindTeammatePost(
        q: String?,
        projectType: String?,
        department: String?,
        className: String?,
        position: String?,
        wantedField: String?,
        bookmarkOnly: Boolean,
        page: Int,
        size: Int,
    ): Page<PostSummary> {
        TODO("Not yet implemented")
    }

    override fun searchFindTeamPost(
        q: String?,
        projectType: String?,
        department: String?,
        className: String?,
        position: String?,
        wantedField: String?,
        bookmarkOnly: Boolean,
        page: Int,
        size: Int,
    ): Page<PostSummary> {
        TODO("Not yet implemented")
    }
}
