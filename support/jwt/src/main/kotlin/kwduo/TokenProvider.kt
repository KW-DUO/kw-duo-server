package kwduo

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class TokenProvider(
    @Value("\${jwt.secret}") secretKey: String,
) {
    private lateinit var key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateTokenDto(
        subject: String,
        authoritiesKey: String,
        authorities: String,
        accessTokenExpireTime: Long,
        refreshTokenExpireTime: Long,
    ): TokenDTO {
        val now = Date().time

        return TokenDTO(
            makeAccessToken(subject, authoritiesKey, authorities, accessTokenExpireTime, now),
            makeRefreshToken(subject, refreshTokenExpireTime, now),
            accessTokenExpireTime,
            refreshTokenExpireTime,
        )
    }

    private fun makeAccessToken(
        subject: String,
        authoritiesKey: String,
        authorities: String,
        accessTokenExpireTime: Long,
        now: Long,
    ) = Jwts.builder()
        .setSubject(subject) // payload "sub": "name"
        .claim(authoritiesKey, authorities) // payload "auth": "ROLE_USER"
        .setExpiration(Date(now + accessTokenExpireTime)) // payload "exp": 1516239022 (예시)
        .signWith(key, SignatureAlgorithm.HS512) // header "alg": "HS512"
        .compact()

    private fun makeRefreshToken(
        subject: String,
        refreshTokenExpireTime: Long,
        now: Long,
    ) = Jwts.builder()
        .setSubject(subject)
        .setExpiration(Date(now + refreshTokenExpireTime))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact()
}
