package kwduo.auth

import com.fasterxml.jackson.databind.JsonNode
import io.swagger.v3.oas.annotations.Operation
import kwduo.oauth.GoogleOAuthAuthorizer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GoogleOAuthController(
    private val googleOAuthAuthorizer: GoogleOAuthAuthorizer,
) {
    @Operation(summary = "구글 로그인")
    @GetMapping("/auth/google")
    fun googleLogin(
        @RequestParam code: String,
    ): String {
        // 구글에 api 쏴서 코드 검증
        val accessToken = googleOAuthAuthorizer.getAccessToken(code)

        // 받은 액세스 토큰으로 유저 정보 확인
        val userResource = googleOAuthAuthorizer.getUserResource(accessToken)

        // 유저 정보의 oAuthId만 리턴
        return getOAuthId(userResource)
    }

    private fun getOAuthId(userResource: JsonNode): String {
        return userResource["id"].asText()
    }
}
