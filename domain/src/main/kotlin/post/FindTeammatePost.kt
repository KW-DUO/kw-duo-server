package post

import member.Member
import member.Position
import java.time.LocalDate

class FindTeammatePost(
    id: Long?,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    interestingField: List<Field>,
    wantedPosition: List<Position>,
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

    isDeleted = isDeleted,
    isClosed = isClosed
) {
}