package kwduo.chatting

import chatting.Chat
import chatting.ChatRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChatJpaRepository : ChatRepository, JpaRepository<ChatEntity, Long> {
    @Query("SELECT c FROM ChatEntity c WHERE c.chattingRoomId = :chattingRoomId")
    override fun findByChattingRoomId(chattingRoomId: Long): List<Chat>
}
