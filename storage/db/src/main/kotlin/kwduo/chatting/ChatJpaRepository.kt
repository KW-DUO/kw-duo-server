package kwduo.chatting

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChatJpaRepository : JpaRepository<ChatEntity, Long> {
    @Query(
        """
          SELECT c FROM ChatEntity c WHERE c.chattingRoomId = :chattingRoomId
        """,
    )
    fun findByChattingRoomId(chattingRoomId: Long): List<ChatEntity>

    @Query(
        """
          SELECT c FROM
            ChatEntity c
            WHERE c.chattingRoomId = :chattingRoomId
            ORDER BY c.chatCreatedAt DESC
            LIMIT 1
        """,
    )
    fun findLastChatByChattingRoomId(chattingRoomId: Long): ChatEntity?
}
