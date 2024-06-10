package kwduo.chatting

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChattingRoomJpaRepository : JpaRepository<ChattingRoomEntity, Long> {
    @Query(
        """
        SELECT cr
        FROM ChattingRoomEntity cr
        WHERE (cr.member1Id = :member1Id AND cr.member2Id = :member2Id)
           OR (cr.member1Id = :member2Id AND cr.member2Id = :member1Id)
    """,
    )
    fun findByParticipantMembersId(
        member1Id: Long,
        member2Id: Long,
    ): ChattingRoomEntity?

    @Query(
        """
        SELECT cr
        FROM ChattingRoomEntity cr
        WHERE cr.member1Id = :memberId
           OR cr.member2Id = :memberId
    """,
    )
    fun findByParticipantMemberId(memberId: Long): List<ChattingRoomEntity>
}
