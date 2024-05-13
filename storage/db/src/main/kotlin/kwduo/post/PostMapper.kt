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

    fun toFindTeamPostDomain(
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

    fun toFindTeammatePostDomain(
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

    fun toFindTeammatePostEntity(post: FindTeammatePost) =
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

    fun toFindTeamPostEntity(post: FindTeamPost) =
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

    fun toInterestingFieldEntity(
        post: Post,
        postId: Long?,
    ) = post.interestingField.map {
        PostInterestingFieldEntity(postId ?: post.id!!, it)
    }

    fun toTechStackEntity(
        post: Post,
        postId: Long?,
    ) = post.techStack.map {
        PostTechStackEntity(postId ?: post.id!!, it)
    }

    fun toPositionEntity(
        post: Post,
        postId: Long?,
    ) = post.wantedPosition.map {
        PostWantedPositionEntity(postId ?: post.id!!, it)
    }
}
