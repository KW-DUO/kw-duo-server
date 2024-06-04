package kwduo.member

interface MemberRepository {
    fun save(member: Member): Member

    fun findById(id: Long): Member?

    fun findByNickname(nickname: String): Member?

    fun findByOAuthId(oAuthId: String): Member?
}
