package kwduo.post

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PostRepositoryImpl(
    private val postJpaRepository: PostJpaRepository,
    private val interestingFieldJpaRepository: PostInterestingFieldJpaRepository,
    private val wantedPositionJpaRepository: PostWantedPositionJpaRepository,
    private val techStackJpaRepository: PostTechStackJpaRepository,
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
        val postEntity = PostMapper.toFindTeammatePostEntity(post)
        val savedPost = postJpaRepository.save(postEntity)

        val stacks = PostMapper.toTechStackEntity(post, savedPost.id!!)
        val wantedPositions = PostMapper.toPositionEntity(post, savedPost.id!!)
        val interestingFields = PostMapper.toInterestingFieldEntity(post, savedPost.id!!)

        savePostMetaData(postEntity, interestingFields, stacks, wantedPositions)

        return PostMapper.toFindTeammatePostDomain(savedPost, interestingFields, stacks, wantedPositions)
    }

    @Transactional
    override fun saveFindTeamPost(post: FindTeamPost): FindTeamPost {
        val postEntity = PostMapper.toFindTeamPostEntity(post)
        val savedPost = postJpaRepository.save(postEntity)

        val stacks = PostMapper.toTechStackEntity(post, savedPost.id!!)
        val wantedPositions = PostMapper.toPositionEntity(post, savedPost.id!!)
        val interestingFields = PostMapper.toInterestingFieldEntity(post, savedPost.id!!)

        savePostMetaData(postEntity, interestingFields, stacks, wantedPositions)

        return PostMapper.toFindTeamPostDomain(savedPost, interestingFields, stacks, wantedPositions)
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

        return PostMapper.toDomain(post, fields, techStack, wantedPositions)
    }

    @Transactional(readOnly = true)
    override fun findFindTeamPostById(id: Long): FindTeamPost? {
        val post =
            postJpaRepository.findFindTeamPostByIdOrNull(id)
                ?: return null

        val fields = interestingFieldJpaRepository.findByPostId(id)
        val techStack = techStackJpaRepository.findByPostId(id)
        val wantedPositions = wantedPositionJpaRepository.findByPostId(id)

        return PostMapper.toFindTeamPostDomain(post, fields, techStack, wantedPositions)
    }

    @Transactional(readOnly = true)
    override fun findFindTeammatePostById(id: Long): FindTeammatePost? {
        val post =
            postJpaRepository.findFindTeammatePostByIdOrNull(id)
                ?: return null

        val fields = interestingFieldJpaRepository.findByPostId(id)
        val techStack = techStackJpaRepository.findByPostId(id)
        val wantedPositions = wantedPositionJpaRepository.findByPostId(id)

        return PostMapper.toFindTeammatePostDomain(post, fields, techStack, wantedPositions)
    }
}
