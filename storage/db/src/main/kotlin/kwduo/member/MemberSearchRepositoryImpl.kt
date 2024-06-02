package kwduo.member

import org.springframework.stereotype.Repository

@Repository
class MemberSearchRepositoryImpl() : MemberSearchRepository {
    override fun findMembersById(memberIds: List<Long>): List<Member> {
        TODO("Not yet implemented")
    }
}
