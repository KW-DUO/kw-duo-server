package member

import token.EmailTokenService

class MemberAuthorizeService(
    private val memberReader: MemberReader,
    private val memberRepository: MemberRepository,
    private val emailTokenService: EmailTokenService,
) {
    fun authorizeEmailToken(
        memberId: Long,
        token: String,
    ) {
        val member = memberReader.findById(memberId)

        val isTokenExist = emailTokenService.findValidToken(member.id!!, token)
        check(isTokenExist) { "유효하지 않은 토큰입니다." }

        member.authenticateEmail()
        memberRepository.save(member)
    }
}
