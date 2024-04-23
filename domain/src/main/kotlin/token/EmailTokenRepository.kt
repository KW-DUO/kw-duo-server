package token

import java.time.LocalDateTime

interface EmailTokenRepository {
    fun save(emailToken: EmailToken): EmailToken

    fun findValidToken(
        memberId: Long,
        token: String,
        now: LocalDateTime = LocalDateTime.now(),
    ): EmailToken?
}
