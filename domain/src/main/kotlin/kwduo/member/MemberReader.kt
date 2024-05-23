package kwduo.member

import org.springframework.stereotype.Component

@Component
class MemberReader(
    private val memberRepository: MemberRepository,
) {
    fun findById(memberId: Long): Member {
        return memberRepository.findById(memberId)
            ?: throw IllegalArgumentException("멤버를 찾을 수 없습니다.")
    }

    fun isExistNickname(nickname: String): Boolean {
        return memberRepository.findByNickname(nickname) != null
    }
}
