package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import kwduo.member.Department
import java.time.LocalDateTime

@DiscriminatorValue("FIND_TEAMMATE")
@Entity
class FindTeammatePostEntity(
    id: Long?,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    className: String?,
    department: Department?,
    @Column(name = "recruit_number", nullable = false)
    var recruitNumber: Int,
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
