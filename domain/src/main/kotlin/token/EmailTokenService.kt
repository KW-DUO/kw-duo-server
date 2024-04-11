package token

class EmailTokenService(
    private val emailTokenRepository: EmailTokenRepository,
) {
    fun createEmailToken(memberId: Long): EmailToken {
        val token = EmailToken.create(memberId)
        emailTokenRepository.save(token)

        return token
    }

    fun findValidToken(memberId: Long, token: String): Boolean {
        val emailToken = emailTokenRepository.findValidToken(memberId, token)
            ?: return false

        check(!emailToken.isUsed) { "유효하지 않은 토큰입니다." }

        emailToken.use()
        return true
    }
}