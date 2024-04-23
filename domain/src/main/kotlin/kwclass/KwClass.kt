package kwclass

import member.Department

data class KwClass(
    val id: Long? = null,
    val className: String,
    val professorName: String,
    val department: Department,
)
