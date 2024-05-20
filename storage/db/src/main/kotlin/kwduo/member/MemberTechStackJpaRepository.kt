package kwduo.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberTechStackJpaRepository : JpaRepository<MemberTechStackEntity, Long> {
    @Query(
        """
        SELECT mts
        FROM MemberTechStackEntity mts
        WHERE mts.memberId = :memberId
    """,
    )
    fun findByMemberId(memberId: Long): List<MemberTechStackEntity>

    @Modifying
    @Query(
        """
        DELETE
        FROM MemberTechStackEntity mts
        WHERE mts.memberId = :id
    """,
    )
    fun deleteByMemberId(id: Long)
}
