package member

import member.dto.MemberJoinRequest
import member.event.MemberJoinEvent
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
}
