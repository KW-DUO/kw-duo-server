package token

import member.Member

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

        emailToken.use()
        return true
    }
}