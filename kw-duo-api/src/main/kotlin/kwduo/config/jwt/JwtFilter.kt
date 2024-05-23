package kwduo.config.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kwduo.TokenValidator
import kwduo.auth.AuthUtil
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtFilter(
    private val authUtil: AuthUtil,
    private val tokenValidator: TokenValidator,
) : OncePerRequestFilter() {
    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Throws(IOException::class, ServletException::class)
    public override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val jwt = resolveToken(request)

        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (tokenValidator.validateToken(jwt)) {
            val authentication = authUtil.getAuthentication(jwt!!)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val accessToken = getAccessToken(request)

        if (accessToken.isNullOrBlank()) {
            return null
        }

        return accessToken
    }

    private fun getAccessToken(request: HttpServletRequest): String? {
        return request.getHeader(HttpHeaders.AUTHORIZATION)?.split("Bearer ")?.last()
    }
}
