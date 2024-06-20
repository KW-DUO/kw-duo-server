package kwduo.post

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack
import kwduo.member.exception.MemberNotAuthorizedException
import kwduo.post.dto.FindTeammatePostEditRequest
import java.time.LocalDateTime

class FindTeammatePost(
    id: Long? = null,
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    className: String?,
    department: Department?,
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
    ) {
    fun update(
        requestMemberId: Long,
        request: FindTeammatePostEditRequest,
    ): Boolean {
        if (!isAuthor(requestMemberId)) {
            throw MemberNotAuthorizedException("작성자만 수정할 수 있습니다.")
        }

        if (!isUpdated(request)) {
            return false
        }

        detail = PostDetail(request.title, request.content)
        projectType = ProjectType.of(request.projectType)
        className = request.className
        department = request.department?.let { Department.valueOf(it) }
        interestingField = request.interestingField.map { Field.valueOf(it) }
        wantedPosition = request.wantedPosition.map { Position.of(it) }
        techStack = request.techStack.map { TechStack.of(it) }
        recruitNumber = request.recruitNumber

        return true
    }

    private fun isUpdated(request: FindTeammatePostEditRequest) =
        detail != PostDetail(request.title, request.content) ||
            projectType != ProjectType.of(request.projectType) ||
            className != request.className ||
            department != request.department?.let { Department.valueOf(it) } ||
            interestingField.map { it.value } != request.interestingField ||
            wantedPosition.map { it.value } != request.wantedPosition ||
            techStack.map { it.value } != request.techStack ||
            recruitNumber != request.recruitNumber
}
