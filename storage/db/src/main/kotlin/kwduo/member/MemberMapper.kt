package kwduo.member

object MemberMapper {
    fun toDomain(
        memberEntity: MemberEntity,
        techStacks: List<MemberTechStackEntity>,
    ) = Member(
        id = memberEntity.id,
        oAuthId = memberEntity.oAuthId,
        profileImgId = memberEntity.profileImgId,
        nickname = memberEntity.nickname,
        bio = memberEntity.bio,
        department = memberEntity.department,
        techStack = techStacks.map { it.techStack },
        position = memberEntity.position,
        emailInfo = KwEmailInfo(memberEntity.email, memberEntity.isAuthenticated),
        githubUrl = memberEntity.githubUrl,
        baekjoonInfo = memberEntity.baekjoonId?.let { BaekJoonInfo(it, memberEntity.tier, memberEntity.tierUpdatedAt!!) },
        joinAt = memberEntity.joinAt,
    )

    fun toMemberEntity(member: Member) =
        MemberEntity(
            id = member.id,
            oAuthId = member.oAuthId,
            profileImgId = member.profileImgId,
            nickname = member.nickname,
            bio = member.bio,
            department = member.department,
            position = member.position,
            email = member.email,
            isAuthenticated = member.isEmailAuthenticated,
            githubUrl = member.githubUrl,
            baekjoonId = member.baekjoonId,
            tier = member.baekjoonInfo?.tier,
            tierUpdatedAt = member.baekjoonInfo?.tierUpdatedAt,
            joinAt = member.joinAt,
        )

    fun toMemberStackEntity(member: Member) =
        member.techStack.map {
            MemberTechStackEntity(
                memberId = member.id!!,
                techStack = it,
            )
        }
}
