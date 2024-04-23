package chatting

interface ChatRepository {
    fun save(chat: Chat): Chat

    fun findByChattingRoomId(chattingRoomId: Long): List<Chat>
}
