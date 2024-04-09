package post

import member.Position

class FindTeamPost(
    id: Long? = null,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    interestingField: List<Field>,
    wantedPosition: List<Position>,
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
    isDeleted = isDeleted,
    isClosed = isClosed
) {
}