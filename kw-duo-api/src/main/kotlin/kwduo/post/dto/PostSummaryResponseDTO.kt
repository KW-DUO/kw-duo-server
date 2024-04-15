package kwduo.post.dto

import kwduo.post.schema.PostSummarySchema

data class PostSummaryResponseDTO(
    val posts: List<PostSummarySchema>,
)
