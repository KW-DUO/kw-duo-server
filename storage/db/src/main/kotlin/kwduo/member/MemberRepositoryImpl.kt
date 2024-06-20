package kwduo.member

import kwduo.member.exception.MemberNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
    private val techStackJpaRepository: MemberTechStackJpaRepository,
) : MemberRepository {
    @Transactional
    override fun save(member: Member): Member {
        val memberEntity = MemberMapper.toMemberEntity(member)
        val savedMemberEntity = memberJpaRepository.save(memberEntity)

        val techStackEntities = MemberMapper.toMemberStackEntity(member, savedMemberEntity.id!!)
        techStackJpaRepository.deleteByMemberId(memberEntity.id!!)

        val savedTechStackEntities = techStackJpaRepository.saveAll(techStackEntities)

        return MemberMapper.toDomain(savedMemberEntity, savedTechStackEntities)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Member? {
        val memberEntity =
            memberJpaRepository.findByIdOrNull(id)
                ?: return null

        val techStacks = techStackJpaRepository.findByMemberId(id)

        return MemberMapper.toDomain(memberEntity, techStacks)
    }

    @Transactional(readOnly = true)
    override fun findByNickname(nickname: String): Member? {
        val memberEntity =
            memberJpaRepository.findByNickname(nickname)
                ?: throw MemberNotFoundException()

        val techStacks = techStackJpaRepository.findByMemberId(memberEntity.id!!)

        return MemberMapper.toDomain(memberEntity, techStacks)
    }

    @Transactional(readOnly = true)
    override fun findByOAuthId(oAuthId: String): Member? {
        val memberEntity =
            memberJpaRepository.findByOAuthId(oAuthId) ?: return null

        val techStacks = techStackJpaRepository.findByMemberId(memberEntity.id!!)

        return MemberMapper.toDomain(memberEntity, techStacks)
    }
}
