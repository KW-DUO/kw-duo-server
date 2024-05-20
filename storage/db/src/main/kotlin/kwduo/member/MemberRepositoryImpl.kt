package kwduo.member

import kwduo.member.exception.MemberNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
    private val techStackJpaRepository: MemberTechStackJpaRepository,
    private val memberMapper: MemberMapper,
) : MemberRepository {
    @Transactional
    override fun save(member: Member): Member {
        val memberEntity = memberMapper.toMemberEntity(member)
        val techStackEntities = memberMapper.toMemberStackEntity(member)

        val savedMemberEntity = memberJpaRepository.save(memberEntity)

        techStackJpaRepository.deleteByMemberId(memberEntity.id!!)
        val savedTechStackEntities = techStackJpaRepository.saveAll(techStackEntities)

        return memberMapper.toDomain(savedMemberEntity, savedTechStackEntities)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Member? {
        val memberEntity =
            memberJpaRepository.findByIdOrNull(id)
                ?: throw MemberNotFoundException()

        val techStacks = techStackJpaRepository.findByMemberId(id)

        return memberMapper.toDomain(memberEntity, techStacks)
    }

    @Transactional(readOnly = true)
    override fun findByNickname(nickname: String): Member? {
        val memberEntity =
            memberJpaRepository.findByNickname(nickname)
                ?: throw MemberNotFoundException()

        val techStacks = techStackJpaRepository.findByMemberId(memberEntity.id!!)

        return memberMapper.toDomain(memberEntity, techStacks)
    }
}
