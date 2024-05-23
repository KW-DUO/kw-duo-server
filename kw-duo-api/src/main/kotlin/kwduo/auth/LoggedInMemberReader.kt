package kwduo.auth

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object LoggedInMemberReader {
    val currentMemberId: Long // SecurityContext 에 유저 정보가 저장되는 시점
        get() {
            check(isLoggedIn()) { "Security Context 에 인증 정보가 없습니다." }
            return getAuth().name.toLong()
        }

    val currentNullishMemberId: Long?
        get() {
            if (!isLoggedIn()) return null
            return getAuth().name.toLong()
        }

    private fun isLoggedIn(): Boolean {
        val auth = getAuth()

        if (auth is AnonymousAuthenticationToken) {
            return false
        }

        return auth?.name != null
    }

    private fun getAuth() = SecurityContextHolder.getContext().authentication
}
