package kwduo.kwclass

import kwduo.member.Department

interface KwClassRepository {
    fun save(kwClass: KwClass): KwClass

    fun findById(id: Long): KwClass?

    fun findAll(): List<KwClass>

    fun findByDepartment(department: Department): List<KwClass>
}
