package apply

interface ApplyRepository {
    fun save(apply: Apply): Apply
    fun existsByMemberAndPostId(memberId: Long, postId: Long): Boolean
}