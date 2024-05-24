package kwduo.post.dto

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack
import kwduo.post.Field
import kwduo.post.FindTeammatePost
import kwduo.post.ProjectType

data class FindTeammatePostWriteRequest(
    val title: String,
    val content: String,
    val authorId: Long,
    val className: String?,
    val department: String,
    val projectType: String,
    val interestingField: List<String>,
    val wantedPosition: List<String>,
    val techStack: List<String>,
    val recruitNumber: Int,
) {
    fun toPost() =
        FindTeammatePost(
            title = title,
            content = content,
            authorId = authorId,
            projectType = ProjectType.of(projectType),
            className = className,
            department = department.let { Department.valueOf(it) },
            interestingField = interestingField.map { Field.valueOf(it) },
            wantedPosition = wantedPosition.map { Position.of(it) },
            techStack = techStack.map { TechStack.of(it) },
            recruitNumber = recruitNumber,
        )
}
