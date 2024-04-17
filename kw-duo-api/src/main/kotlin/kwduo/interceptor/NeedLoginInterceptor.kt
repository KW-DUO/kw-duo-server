package kwduo.interceptor
//
// import jakarta.servlet.http.HttpServletRequest
// import jakarta.servlet.http.HttpServletResponse
// import org.springframework.stereotype.Component
// import org.springframework.web.method.HandlerMethod
// import org.springframework.web.servlet.HandlerInterceptor
//
// @Component
// class NeedLoginInterceptor(
//    private val tokenProvider: TokenProvider
// ) : HandlerInterceptor {
//    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        if (!hasNeedLoginAnnotation(handler)) {
//            return true
//        }
//
//        if (!hasValidAccessToken(request)) {
//            throw UserNotAuthorizedException()
//        }
//
//        return true
//    }
//
//    private fun hasValidAccessToken(request: HttpServletRequest): Boolean {
//        return getAccessCookie(request)
//            ?.let { tokenProvider.validateToken(it) }
//            ?: false
//    }
//
//    private fun hasNeedLoginAnnotation(handler: Any): Boolean {
//        return handler is HandlerMethod &&
//                handler.method.isAnnotationPresent(NeedLogin::class.java)
//    }
// }
