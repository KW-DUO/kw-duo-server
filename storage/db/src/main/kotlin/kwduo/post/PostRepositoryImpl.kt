package kwduo.post

import kwduo.post.exception.PostNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PostRepositoryImpl(
    private val postJpaRepository: PostJpaRepository,
    private val interestingFieldJpaRepository: PostInterestingFieldJpaRepository,
    private val wantedPositionJpaRepository: PostWantedPositionJpaRepository,
    private val techStackJpaRepository: PostTechStackJpaRepository,
    private val postMapper: PostMapper,
) : PostRepository {
    @Transactional
    override fun save(post: Post): Post {
        val postEntity = postMapper.toEntity(post)

        val stacks = postMapper.toTechStackEntity(post)
        val wantedPositions = postMapper.toPositionEntity(post)
        val interestingFields = postMapper.toInterestingFieldEntity(post)

        val savedPost = postJpaRepository.save(postEntity)

        interestingFieldJpaRepository.deleteByPostId(postEntity.id!!)
        techStackJpaRepository.deleteByPostId(postEntity.id!!)
        wantedPositionJpaRepository.deleteByPostId(postEntity.id!!)

        interestingFieldJpaRepository.saveAll(interestingFields)
        techStackJpaRepository.saveAll(stacks)
        wantedPositionJpaRepository.saveAll(wantedPositions)

        return postMapper.toDomain(savedPost, interestingFields, stacks, wantedPositions)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Post? {
        val post =
            postJpaRepository.findByIdOrNull(id)
                ?: throw PostNotFoundException()

        val fields = interestingFieldJpaRepository.findByPostId(id)
        val techStack = techStackJpaRepository.findByPostId(id)
        val wantedPositions = wantedPositionJpaRepository.findByPostId(id)

        return postMapper.toDomain(post, fields, techStack, wantedPositions)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Post> {
        TODO("Not yet implemented")
    }
}
