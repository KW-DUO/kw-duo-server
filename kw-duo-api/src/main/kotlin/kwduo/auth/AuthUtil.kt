package kwduo.auth

import kwduo.TokenValidator
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class AuthUtil(
    private val tokenValidator: TokenValidator,
) {
    fun getAuthentication(accessToken: String): Authentication {
        // 토큰 복호화
        val claims = tokenValidator.parseClaims(accessToken)
        requireNotNull(claims[AUTHORITIES_KEY]) { "권한 정보가 없는 토큰입니다." }

        // 클레임에서 권한 정보 가져오기
        val authorities =
            claims[AUTHORITIES_KEY].toString()
                .split(",".toRegex())
                .dropLastWhile { it.isEmpty() }
                .map { SimpleGrantedAuthority(it) }

        // UserDetails 객체를 만들어서 Authentication 리턴
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
        private val ACCESS_TOKEN_EXPIRE_TIME = Duration.ofMinutes(30).toMillis()
        private val REFRESH_TOKEN_EXPIRE_TIME = Duration.ofDays(7).toMillis()
    }
}
