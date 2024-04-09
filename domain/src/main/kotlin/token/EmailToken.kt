package token

import member.Member
import util.RandomSlugMaker
import java.time.LocalDateTime

class EmailToken(
    val id: Long? = null,
    val memberId: Long,
    val token: String,
    var isUsed: Boolean = false,
    val expirationTime: LocalDateTime,
) {
    fun use() {
        isUsed = true
    }

    companion object {
        fun create(
            memberId: Long,
            now: LocalDateTime = LocalDateTime.now(),
        ): EmailToken {
            return EmailToken(
                id = null,
                memberId = memberId,
                token = RandomSlugMaker.makeSlug(TOKEN_LENGTH),
                expirationTime = now.plusMinutes(EXPIRATION_MINUTES),
            )
        }

        private const val TOKEN_LENGTH = 8
        private const val EXPIRATION_MINUTES = 30L
    }
}