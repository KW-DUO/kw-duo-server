package apply

class ApplyService(
    private val applyRepository: ApplyRepository,
) {
    fun apply(
        memberId: Long,
        postId: Long,
    ) {
        check(!isApplied(memberId, postId)) { "이미 지원 신청한 게시물입니다." }

        val apply = Apply(memberId = memberId, postId = postId)
        applyRepository.save(apply)
    }

    fun isApplied(
        memberId: Long,
        postId: Long,
    ): Boolean {
        return applyRepository.existsByMemberAndPostId(memberId, postId)
    }
}
