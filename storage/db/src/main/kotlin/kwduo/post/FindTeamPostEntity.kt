package kwduo.post

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.time.LocalDateTime

@DiscriminatorValue("FIND_TEAM")
@Entity
class FindTeamPostEntity(
    id: Long?,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
    writtenAt: LocalDateTime,
) : PostEntity(
        id,
        title,
        content,
        authorId,
        projectType,
        isDeleted,
        isClosed,
        writtenAt,
    )
