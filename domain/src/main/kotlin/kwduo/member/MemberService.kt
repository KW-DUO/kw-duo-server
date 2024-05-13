package kwduo.member

import kwduo.member.dto.MemberJoinRequest
import kwduo.member.dto.MemberUpdateInfoRequest
import kwduo.member.event.MemberInfoUpdateEvent
import kwduo.member.event.MemberJoinEvent
import org.springframework.context.ApplicationEventPublisher

class MemberService(
    private val memberReader: MemberReader,
    private val memberRepository: MemberRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun join(request: MemberJoinRequest) {
        val member = request.toMember()

//        if (member.profileImgId != null) {
//            imageService.use(member.profileImgId)
//        }
//
//        updateBaekjoonTier(member)

        val savedMember = memberRepository.save(member)

        eventPublisher.publishEvent(MemberJoinEvent(savedMember))
    }

    fun updateInfo(
        requestMemberId: Long,
        request: MemberUpdateInfoRequest,
    ) {
        val member = memberReader.findById(requestMemberId)

        check(memberReader.isExistNickname(request.nickname)) { "이미 사용중인 닉네임입니다." }

        member.updateInfo(request)
        memberRepository.save(member)

        eventPublisher.publishEvent(MemberInfoUpdateEvent(member))
    }
}
