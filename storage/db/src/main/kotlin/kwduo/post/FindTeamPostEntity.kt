package kwduo.post

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import kwduo.member.Department
import java.time.LocalDateTime

@DiscriminatorValue("FIND_TEAM")
@Entity
class FindTeamPostEntity(
    id: Long?,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    className: String?,
    department: Department,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
    writtenAt: LocalDateTime,
) : PostEntity(
        id,
        title,
        content,
        authorId,
        projectType,
        className,
        department,
        isDeleted,
        isClosed,
        writtenAt,
    )
