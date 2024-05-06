package post

import member.Position
import member.TechStack

class FindTeammatePost(
    id: Long? = null,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    interestingField: List<Field>,
    wantedPosition: List<Position>,
    techStack: List<TechStack>,
    var recruitNumber: Int,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
) : Post(
        id = id,
        title = title,
        content = content,
        authorId = authorId,
        projectType = projectType,
        interestingField = interestingField,
        wantedPosition = wantedPosition,
        techStack = techStack,
        isDeleted = isDeleted,
        isClosed = isClosed,
    )
