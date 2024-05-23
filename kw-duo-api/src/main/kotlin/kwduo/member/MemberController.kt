package kwduo.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.auth.LoggedInMemberReader
import kwduo.member.dto.MemberInfoResponseDTO
import kwduo.member.dto.MemberInfoUpdateRequestDTO
import kwduo.member.dto.MemberUpdateInfoRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member")
@RestController
class MemberController(
    val memberService: MemberService,
) {
    @NeedLogin
    @Operation(summary = "회원 정보 조회")
    @GetMapping("/members/info")
    fun getMemberInfo(): MemberInfoResponseDTO {
        val requestMemberId = LoggedInMemberReader.currentMemberId

        val memberInfo = memberService.getMemberInfo(requestMemberId)

        return MemberInfoResponseDTO(memberInfo)
    }

    @NeedLogin
    @Operation(summary = "회원 정보 수정")
    @PostMapping("/members/info")
    fun updateMemberInfo(
        @RequestBody request: MemberInfoUpdateRequestDTO,
    ) {
        memberService.updateInfo(
            LoggedInMemberReader.currentMemberId,
            MemberUpdateInfoRequest(
                profileImgId = request.profileImgId,
                nickname = request.nickname,
                bio = request.bio,
                techStack = request.techStack,
                position = request.position,
                githubUrl = request.githubUrl,
                baekjoonId = request.baekjoonId,
            ),
        )
    }
}
