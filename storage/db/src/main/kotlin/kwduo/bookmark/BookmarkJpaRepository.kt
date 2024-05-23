package kwduo.bookmark

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookmarkJpaRepository : JpaRepository<BookmarkEntity, Long> {
    @Query(
        """
        SELECT b
        FROM BookmarkEntity b
        WHERE b.memberId = :requestMemberId
          AND b.postId = :postId
          AND b.isBookMarked = true
    """,
    )
    fun findByMemberIdAndPostId(
        requestMemberId: Long,
        postId: Long,
    ): BookmarkEntity?
}
