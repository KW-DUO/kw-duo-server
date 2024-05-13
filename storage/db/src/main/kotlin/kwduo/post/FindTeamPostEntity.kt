package kwduo.post

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@DiscriminatorValue("FIND_TEAM")
@Entity
class FindTeamPostEntity(
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
) : PostEntity(
        title,
        content,
        authorId,
        projectType,
        isDeleted,
        isClosed,
    )
