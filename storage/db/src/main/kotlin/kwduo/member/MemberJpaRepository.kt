package kwduo.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    @Query(
        """
        SELECT m
        FROM MemberEntity m
        WHERE m.nickname = :nickname
    """,
    )
    fun findByNickname(nickname: String): MemberEntity?

    @Query(
        """
        SELECT COUNT(m)
        FROM MemberEntity m
    """,
    )
    fun getAllCount(): Long

    @Query(
        """
        SELECT m
        FROM MemberEntity m
        WHERE m.oAuthId = :oAuthId
    """,
    )
    fun findByOAuthId(oAuthId: String): MemberEntity?
}
