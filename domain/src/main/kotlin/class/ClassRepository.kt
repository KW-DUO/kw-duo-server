package `class`

import member.Department

interface ClassRepository {
    fun save(`class`: Class): Class
    fun findById(id: Long): Class?
    fun findAll(): List<Class>
    fun findByDepartment(department: Department): List<Class>
}