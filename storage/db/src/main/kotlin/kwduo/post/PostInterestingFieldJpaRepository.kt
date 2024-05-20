package kwduo.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostInterestingFieldJpaRepository : JpaRepository<PostInterestingFieldEntity, Long> {
    @Query(
        """
        SELECT pif
        FROM PostInterestingFieldEntity pif
        WHERE pif.postId = :postId
    """,
    )
    fun findByPostId(postId: Long): List<PostInterestingFieldEntity>

    @Modifying
    @Query(
        """
        DELETE FROM PostInterestingFieldEntity pif
        WHERE pif.postId = :postId
    """,
    )
    fun deleteByPostId(postId: Long)
}
