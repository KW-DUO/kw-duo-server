package kwduo.post

import kwduo.post.dto.PostSummary
import org.springframework.stereotype.Service

@Service
class PostSearchService(
    private val postSearchRepository: PostSearchRepository,
) {
    fun searchFindTeammatePost(
        q: String?,
        projectType: String?,
        department: String?,
        className: String?,
        position: String?,
        wantedField: String?,
        bookmarkOnly: Boolean,
        page: Int,
        size: Int,
    ): PostSummary {
        val posts =
            postSearchRepository.searchFindTeammatePost(
                q = q,
                projectType = projectType,
                department = department,
                className = className,
                position = position,
                wantedField = wantedField,
                bookmarkOnly = bookmarkOnly,
                page = page,
                size = size,
            )
        TODO("Not yet implemented")
    }
}
