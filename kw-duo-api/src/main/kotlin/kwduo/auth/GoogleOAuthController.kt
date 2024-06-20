package kwduo.auth

import com.fasterxml.jackson.databind.JsonNode
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.TokenDTO
import kwduo.TokenProvider
import kwduo.auth.dto.GoogleLoginResponseDTO
import kwduo.member.MemberService
import kwduo.oauth.GoogleOAuthAuthorizer
import org.springframework.http.ResponseCookie
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
class GoogleOAuthController(
    private val googleOAuthAuthorizer: GoogleOAuthAuthorizer,
    private val memberService: MemberService,
    private val tokenProvider: TokenProvider,
) {
    @Operation(summary = "구글 로그인")
    @GetMapping("/auth/google")
    fun googleLogin(
        @RequestParam(name = "code") accessToken: String,
    ): GoogleLoginResponseDTO {
        val userResource = googleOAuthAuthorizer.getUserResource(accessToken)
        val oAuthId = getOAuthId(userResource)

        val member =
            memberService.findByOAuthId(oAuthId)
                ?: return GoogleLoginResponseDTO(oAuthId, true, null)

        val token = createToken(member.id!!)

        return GoogleLoginResponseDTO(oAuthId, false, token.accessToken)
    }

    private fun getOAuthId(userResource: JsonNode): String {
        return userResource["id"].asText()
    }

    private fun createToken(
        memberId: Long,
        authorities: String = "ROLE_USER",
    ): TokenDTO {
        return tokenProvider.generateTokenDto(
            subject = memberId.toString(),
            authoritiesKey = "auth",
            authorities = authorities,
        )
    }

    private fun createAccessTokenCookie(
        accessToken: String,
        expiresIn: Long,
    ): ResponseCookie {
        return ResponseCookie.from("accessToken", accessToken)
            .httpOnly(true)
            .secure(true)
            .sameSite("None")
            .path("/")
            .domain("kw-duo.vercel.app")
            .maxAge(expiresIn / 1000)
            .build()
    }

    private fun createRefreshTokenCookie(
        refreshToken: String,
        expiresIn: Long,
    ): ResponseCookie {
        return ResponseCookie.from("refreshToken", refreshToken)
            .httpOnly(true)
            .secure(true)
            .sameSite("None")
            .path("/")
            .domain("kw-duo.vercel.app")
            .maxAge(expiresIn / 1000)
            .build()
    }
}
