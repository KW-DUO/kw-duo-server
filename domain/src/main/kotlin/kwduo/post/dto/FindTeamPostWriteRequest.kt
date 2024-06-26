package kwduo.post.dto

import kwduo.member.Position
import kwduo.member.TechStack
import kwduo.post.Field
import kwduo.post.FindTeamPost
import kwduo.post.ProjectType

class FindTeamPostWriteRequest(
    val title: String,
    val content: String,
    val authorId: Long,
    val projectType: String,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
) {
    fun toPost() =
        FindTeamPost(
            title = title,
            content = content,
            authorId = authorId,
            projectType = ProjectType.of(projectType),
            interestingField = interestingField.map { Field.valueOf(it) },
            wantedPosition = wantedPosition.map { Position.of(it) },
            techStack = techStack.map { TechStack.of(it) },
        )
}
