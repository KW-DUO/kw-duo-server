package kwduo.apply

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ApplyRepositoryImpl(
    private val applyJpaRepository: ApplyJpaRepository,
) : ApplyRepository {
    @Transactional
    override fun save(apply: Apply): Apply {
        val applyEntity = ApplyMapper.toEntity(apply)

        val savedEntity = applyJpaRepository.save(applyEntity)
        return ApplyMapper.toDomain(savedEntity)
    }

    @Transactional(readOnly = true)
    override fun existsByMemberAndPostId(
        memberId: Long,
        postId: Long,
    ): Boolean {
        return applyJpaRepository.existsByMemberIdAndPostId(memberId, postId)
    }

    @Transactional(readOnly = true)
    override fun findByPostId(postId: Long): List<Apply> {
        return applyJpaRepository.findByPostId(postId)
            .map { ApplyMapper.toDomain(it) }
    }
}
