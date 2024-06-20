package kwduo.post.dto

import kwduo.member.Department
import kwduo.member.Position
import kwduo.post.Field
import kwduo.post.ProjectType

data class PostSearchRequest(
    val q: String?,
    val projectType: String?,
    val department: String?,
    val className: String?,
    val position: String?,
    val wantedField: String?,
    val bookmarkOnly: Boolean,
    val page: Int,
    val size: Int,
) {
    fun getProjectType() = projectType?.let { ProjectType.valueOf(it) }

    fun getDepartment() = department?.let { Department.valueOf(it) }

    fun getPosition() = position?.let { Position.valueOf(it) }

    fun getWantedField() = wantedField?.let { Field.valueOf(it) }
}
