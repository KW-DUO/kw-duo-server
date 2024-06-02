package kwduo.member

import kwduo.member.dto.MemberInfo
import kwduo.member.dto.MemberJoinRequest
import kwduo.member.dto.MemberUpdateInfoRequest
import kwduo.member.event.MemberInfoUpdateEvent
import kwduo.member.event.MemberJoinEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberReader: MemberReader,
    private val memberRepository: MemberRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun join(request: MemberJoinRequest): Member {
        val member = request.toMember()

//        updateBaekjoonTier(member)

        val savedMember = memberRepository.save(member)

        eventPublisher.publishEvent(MemberJoinEvent(savedMember))

        return savedMember
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

    fun getMemberInfo(memberId: Long): MemberInfo {
        val member = memberReader.findById(memberId)

        return MemberInfo(
            id = member.id!!,
            nickname = member.nickname,
            department = member.department,
            baekjoonId = member.baekjoonId,
            position = member.position,
            techStack = member.techStack,
            bio = member.bio,
            githubUrl = member.githubUrl,
        )
    }
}
