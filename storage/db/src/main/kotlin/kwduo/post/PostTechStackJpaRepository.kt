package kwduo.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostTechStackJpaRepository : JpaRepository<PostTechStackEntity, Long> {
    @Query(
        """
        SELECT pts
        FROM PostTechStackEntity pts
        WHERE pts.postId = :postId
    """,
    )
    fun findByPostId(postId: Long): List<PostTechStackEntity>

    @Modifying
    @Query(
        """
        DELETE FROM PostTechStackEntity pts
        WHERE pts.postId = :postId
    """,
    )
    fun deleteByPostId(postId: Long)
}
