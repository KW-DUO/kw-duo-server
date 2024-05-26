package kwduo.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostJpaRepository : JpaRepository<PostEntity, Long> {
    @Query(
        """
        SELECT p
        FROM FindTeamPostEntity p
        WHERE p.id = :id
          AND p.isDeleted = false
    """,
    )
    fun findFindTeamPostByIdOrNull(id: Long): FindTeamPostEntity?

    @Query(
        """
        SELECT p
        FROM FindTeammatePostEntity p
        WHERE p.id = :id
          AND p.isDeleted = false
    """,
    )
    fun findFindTeammatePostByIdOrNull(id: Long): FindTeammatePostEntity?
}
