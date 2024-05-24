package kwduo.post

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack
import java.time.LocalDateTime

class FindTeammatePost(
    id: Long? = null,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    className: String?,
    department: Department,
    interestingField: List<Field>,
    wantedPosition: List<Position>,
    techStack: List<TechStack>,
    var recruitNumber: Int,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
    createdAt: LocalDateTime = LocalDateTime.now(),
) : Post(
        id = id,
        title = title,
        content = content,
        authorId = authorId,
        projectType = projectType,
        className = className,
        department = department,
        interestingField = interestingField,
        wantedPosition = wantedPosition,
        techStack = techStack,
        isDeleted = isDeleted,
        isClosed = isClosed,
        createdAt = createdAt,
    )
