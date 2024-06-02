package kwduo.member

interface MemberSearchRepository {
    fun findMembersById(memberIds: List<Long>): List<Member>
}
