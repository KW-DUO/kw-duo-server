package kwduo.member.dto

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack

data class MemberInfo(
    val id: Long,
    val nickname: String,
    val department: Department,
    val position: Position,
    val bio: String,
    val techStack: List<TechStack>,
    val githubUrl: String?,
    val baekjoonId: String?,
)
