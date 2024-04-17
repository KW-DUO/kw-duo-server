package kwduo.member

import kwduo.member.dto.MemberJoinRequestDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberAuthController(
//    private val memberService: MemberService,
) {
    @PostMapping("/members/join")
    fun join(
        @RequestBody request: MemberJoinRequestDTO
    ) {
//        memberService.join(request.toRequest())
        // TODO: 액세스 토큰 내리기
    }
}