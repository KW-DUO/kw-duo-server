package kwduo.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostWantedPositionJpaRepository : JpaRepository<PostWantedPositionEntity, Long> {
    @Query(
        """
        SELECT pwp
        FROM PostWantedPositionEntity pwp
        WHERE pwp.postId = :postId
    """,
    )
    fun findByPostId(postId: Long): List<PostWantedPositionEntity>

    @Modifying
    @Query(
        """
        DELETE
        FROM PostWantedPositionEntity pwp
        WHERE pwp.postId = :postId
    """,
    )
    fun deleteByPostId(postId: Long)
}
