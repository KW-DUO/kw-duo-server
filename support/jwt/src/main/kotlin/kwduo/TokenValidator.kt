package kwduo

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key

@Component
class TokenValidator(
    @Value("\${jwt.secret}") secretKey: String,
) {
    private lateinit var key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun validateToken(token: String?): Boolean {
        if (token == null) return false

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            // JWT 서명이 올바르지 않거나, JWT 구조가 올바르지 않음
        } catch (_: MalformedJwtException) {
        } catch (e: ExpiredJwtException) {
            // JWT 토큰이 만료됨
        } catch (e: UnsupportedJwtException) {
            // 지원되지 않는 JWT 토큰
        } catch (e: IllegalArgumentException) {
            // JWT 토큰이 잘못되었음
        }
        return false
    }

    fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}
