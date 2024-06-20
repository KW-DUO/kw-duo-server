package kwduo.member

import org.springframework.stereotype.Service

@Service
class MemberSearchService(
    private val memberReader: MemberReader,
) {
    fun findMembersById(memberIds: List<Long>): List<Member> {
        return memberIds.map { memberReader.findById(it) }
    }
}
