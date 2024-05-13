package post.dto

import member.Position
import member.TechStack
import post.Field
import post.FindTeammatePost
import post.ProjectType

data class FindTeammatePostWriteRequest(
    val title: String,
    val content: String,
    val authorId: Long,
    val projectType: String,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
    val recruitNumber: Int,
) {
    fun toPost(): FindTeammatePost {
        return FindTeammatePost(
            title = title,
            content = content,
            authorId = authorId,
            projectType = ProjectType.of(projectType),
            interestingField = interestingField.map { Field.valueOf(it) },
            wantedPosition = wantedPosition.map { Position.of(it) },
            techStack = techStack.map { TechStack.of(it) },
            recruitNumber = recruitNumber,
        )
    }
}
