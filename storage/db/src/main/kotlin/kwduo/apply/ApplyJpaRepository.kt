package kwduo.apply

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ApplyJpaRepository : JpaRepository<ApplyEntity, Long> {
    @Query(
        """
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM ApplyEntity a
        WHERE a.memberId = :memberId
        AND a.postId = :postId
    """,
    )
    fun existsByMemberIdAndPostId(
        memberId: Long,
        postId: Long,
    ): Boolean

    @Query(
        """
        SELECT a
        FROM ApplyEntity a
        WHERE a.postId = :postId
    """,
    )
    fun findByPostId(postId: Long): List<ApplyEntity>
}
