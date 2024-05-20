package kwduo.apply

import org.springframework.stereotype.Repository

@Repository
class ApplyRepositoryImpl(
    private val applyJpaRepository: ApplyJpaRepository,
    private val applyMapper: ApplyMapper,
) : ApplyRepository {
    override fun save(apply: Apply): Apply {
        val applyEntity = applyMapper.toEntity(apply)

        val savedEntity = applyJpaRepository.save(applyEntity)
        return applyMapper.toDomain(savedEntity)
    }

    override fun existsByMemberAndPostId(
        memberId: Long,
        postId: Long,
    ): Boolean {
        return applyJpaRepository.existsByMemberIdAndPostId(memberId, postId)
    }
}
