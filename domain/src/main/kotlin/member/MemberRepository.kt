package member

interface MemberRepository {
    fun save(member: Member): Member

    fun findById(id: Long): Member?

    fun findByNickname(nickname: String): Member?

    fun findByTechStack(stack: String): List<Member>
}
