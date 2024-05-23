package kwduo.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.member.dto.MemberJoinRequestDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member")
@RestController
class MemberAuthController(
//    private val memberService: MemberService,
) {
    @Operation(summary = "회원 가입")
    @PostMapping("/members/join")
    fun join(
        @RequestBody request: MemberJoinRequestDTO,
    ) {
//        memberService.join(request.toRequest())
        // TODO: 액세스 토큰 내리기
    }

    @Operation(summary = "로그인")
    @PostMapping("/members/login")
    fun login() {
    }

    @Operation(summary = "테스트용 로그인", description = "강제로 로그인합니다")
    @PostMapping("/members/login/test")
    fun loginForTest() {
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/members/logout")
    fun logout(): ResponseEntity<Unit> {
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, createAccessTokenCookie("", 0).toString())
            .header(HttpHeaders.SET_COOKIE, createRefreshTokenCookie("", 0).toString())
            .build()
    }

    private fun createAccessTokenCookie(
        accessToken: String,
        expiresIn: Long,
    ): ResponseCookie {
        return ResponseCookie.from("accessToken", accessToken)
            .httpOnly(true)
            .secure(true)
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
            .maxAge(expiresIn / 1000)
            .build()
    }
}
