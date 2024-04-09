package `class`

import member.Department

data class Class(
    val id: Long? = null,
    val className: String,
    val professorName: String,
    val department: Department,
)
