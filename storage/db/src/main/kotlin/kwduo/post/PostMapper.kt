package kwduo.post

import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun toDomain(
        post: PostEntity,
        fields: List<PostInterestingFieldEntity>,
        techStack: List<PostTechStackEntity>,
        wantedPositions: List<PostWantedPositionEntity>,
    ): Post {
        return when (post) {
            is FindTeammatePostEntity -> toFindTeammatePostDomain(post, fields, techStack, wantedPositions)
            is FindTeamPostEntity -> toFindTeamPostDomain(post, fields, techStack, wantedPositions)
            else -> throw IllegalArgumentException("Unknown post type")
        }
    }

    private fun toFindTeamPostDomain(
        post: FindTeamPostEntity,
        fields: List<PostInterestingFieldEntity>,
        techStack: List<PostTechStackEntity>,
        wantedPositions: List<PostWantedPositionEntity>,
    ) = FindTeamPost(
        id = post.id,
        title = post.title,
        content = post.content,
        authorId = post.authorId,
        projectType = post.projectType,
        interestingField = fields.map { it.field },
        wantedPosition = wantedPositions.map { it.position },
        techStack = techStack.map { it.techStack },
        isDeleted = post.isDeleted,
        isClosed = post.isClosed,
        createdAt = post.writtenAt,
    )

    private fun toFindTeammatePostDomain(
        post: FindTeammatePostEntity,
        fields: List<PostInterestingFieldEntity>,
        techStack: List<PostTechStackEntity>,
        wantedPositions: List<PostWantedPositionEntity>,
    ) = FindTeammatePost(
        id = post.id,
        title = post.title,
        content = post.content,
        authorId = post.authorId,
        projectType = post.projectType,
        interestingField = fields.map { it.field },
        wantedPosition = wantedPositions.map { it.position },
        techStack = techStack.map { it.techStack },
        recruitNumber = post.recruitNumber,
        isDeleted = post.isDeleted,
        isClosed = post.isClosed,
        createdAt = post.writtenAt,
    )

    fun toEntity(post: Post): PostEntity {
        return when (post) {
            is FindTeammatePost -> toFindTeammatePostEntity(post)
            is FindTeamPost -> toFindTeamPostEntity(post)
            else -> throw IllegalArgumentException("Unknown post type")
        }
    }

    private fun toFindTeammatePostEntity(post: FindTeammatePost) =
        FindTeammatePostEntity(
            id = post.id,
            title = post.title,
            content = post.content,
            authorId = post.authorId,
            projectType = post.projectType,
            recruitNumber = post.recruitNumber,
            isDeleted = post.isDeleted,
            isClosed = post.isClosed,
            writtenAt = post.createdAt,
        )

    private fun toFindTeamPostEntity(post: FindTeamPost) =
        FindTeamPostEntity(
            id = post.id,
            title = post.title,
            content = post.content,
            authorId = post.authorId,
            projectType = post.projectType,
            isDeleted = post.isDeleted,
            isClosed = post.isClosed,
            writtenAt = post.createdAt,
        )

    fun toInterestingFieldEntity(post: Post) =
        post.interestingField.map {
            PostInterestingFieldEntity(post.id!!, it)
        }

    fun toTechStackEntity(post: Post) =
        post.techStack.map {
            PostTechStackEntity(post.id!!, it)
        }

    fun toPositionEntity(post: Post) =
        post.wantedPosition.map {
            PostWantedPositionEntity(post.id!!, it)
        }
}
