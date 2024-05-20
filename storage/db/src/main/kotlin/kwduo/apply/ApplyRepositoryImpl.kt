package kwduo.apply

import org.springframework.stereotype.Repository

@Repository
class ApplyRepositoryImpl(
    private val applyJpaRepository: ApplyJpaRepository,
) : ApplyRepository {
    override fun save(apply: Apply): Apply {
        val applyEntity = ApplyMapper.toEntity(apply)

        val savedEntity = applyJpaRepository.save(applyEntity)
        return ApplyMapper.toDomain(savedEntity)
    }

    override fun existsByMemberAndPostId(
        memberId: Long,
        postId: Long,
    ): Boolean {
        return applyJpaRepository.existsByMemberIdAndPostId(memberId, postId)
    }
}
