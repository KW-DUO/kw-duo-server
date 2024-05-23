package kwduo.post.dto

import kwduo.post.schema.PostSummarySchema
import kwduo.util.Page

data class PostSummaryResponseDTO(
    val posts: List<PostSummarySchema>,
    val totalCount: Int,
    val currentPage: Int,
    val totalPage: Int,
) {
    constructor(result: Page<PostSummary>) : this(
        posts = emptyList(),
        totalCount = result.totalCount,
        totalPage = result.totalPages,
        currentPage = result.currentPage,
    )
}
