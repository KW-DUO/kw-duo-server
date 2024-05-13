package kwduo.post

import kwduo.post.dto.PostSummary
import kwduo.util.Page

interface PostSearchRepository {
    fun searchFindTeammatePost(
        q: String?,
        projectType: String?,
        department: String?,
        className: String?,
        position: String?,
        wantedField: String?,
        bookmarkOnly: Boolean,
        notClosedOnly: Boolean,
        page: Int,
        size: Int,
    ): Page<PostSummary>

    fun searchFindTeamPost(
        q: String?,
        projectType: String?,
        department: String?,
        className: String?,
        position: String?,
        wantedField: String?,
        bookmarkOnly: Boolean,
        notClosedOnly: Boolean,
        page: Int,
        size: Int,
    ): Page<PostSummary>
}
