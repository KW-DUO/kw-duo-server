package kwduo.post.dto

import kwduo.member.schema.MemberSummarySchema

data class PostApplicantResponseDTO(
    val applicants: List<MemberSummarySchema>,
)
