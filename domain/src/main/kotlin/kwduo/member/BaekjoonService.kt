package kwduo.member

class BaekjoonService(
    private val baekjoonTierReader: BaekjoonTierReader,
    private val memberRepository: MemberRepository,
) {
    fun refreshBaekjoonTier(member: Member) {
        check(member.hasBaekjoonInfo()) { "백준 정보가 없으면 티어를 갱신할 수 없습니다." }

        val tier = baekjoonTierReader.getTier(member.baekjoonId!!)
        member.updateBaekjoonTier(tier)

        memberRepository.save(member)
    }
}
