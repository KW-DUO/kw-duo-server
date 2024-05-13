package kwduo.post

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
        return when (post) {
            is FindTeammatePost -> saveFindTeammatePost(post)
            is FindTeamPost -> saveFindTeamPost(post)
            else -> throw IllegalArgumentException("Unknown post type")
        }
    }

    @Transactional
    override fun saveFindTeammatePost(post: FindTeammatePost): FindTeammatePost {
        val postEntity = postMapper.toFindTeammatePostEntity(post)
        val savedPost = postJpaRepository.save(postEntity)

        val stacks = postMapper.toTechStackEntity(post, savedPost.id!!)
        val wantedPositions = postMapper.toPositionEntity(post, savedPost.id!!)
        val interestingFields = postMapper.toInterestingFieldEntity(post, savedPost.id!!)

        savePostMetaData(postEntity, interestingFields, stacks, wantedPositions)

        return postMapper.toFindTeammatePostDomain(savedPost, interestingFields, stacks, wantedPositions)
    }

    @Transactional
    override fun saveFindTeamPost(post: FindTeamPost): FindTeamPost {
        val postEntity = postMapper.toFindTeamPostEntity(post)
        val savedPost = postJpaRepository.save(postEntity)

        val stacks = postMapper.toTechStackEntity(post, savedPost.id!!)
        val wantedPositions = postMapper.toPositionEntity(post, savedPost.id!!)
        val interestingFields = postMapper.toInterestingFieldEntity(post, savedPost.id!!)

        savePostMetaData(postEntity, interestingFields, stacks, wantedPositions)

        return postMapper.toFindTeamPostDomain(savedPost, interestingFields, stacks, wantedPositions)
    }

    private fun savePostMetaData(
        postEntity: PostEntity,
        interestingFields: List<PostInterestingFieldEntity>,
        stacks: List<PostTechStackEntity>,
        wantedPositions: List<PostWantedPositionEntity>,
    ) {
        interestingFieldJpaRepository.deleteByPostId(postEntity.id!!)
        techStackJpaRepository.deleteByPostId(postEntity.id!!)
        wantedPositionJpaRepository.deleteByPostId(postEntity.id!!)

        interestingFieldJpaRepository.saveAll(interestingFields)
        techStackJpaRepository.saveAll(stacks)
        wantedPositionJpaRepository.saveAll(wantedPositions)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Post? {
        val post =
            postJpaRepository.findByIdOrNull(id)
                ?: return null

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
