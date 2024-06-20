package kwduo.chatting

interface ChatRepository {
    fun save(chat: Chat): Chat

    fun findById(chatId: Long): Chat?

    fun findByChattingRoomId(chattingRoomId: Long): List<Chat>

    fun findLastChatByChattingRoomId(chattingRoomId: Long): Chat?
}
