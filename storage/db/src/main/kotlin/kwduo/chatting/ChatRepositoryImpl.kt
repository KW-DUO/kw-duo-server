package kwduo.chatting

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ChatRepositoryImpl(
    private val chatJpaRepository: ChatJpaRepository,
) : ChatRepository {
    @Transactional
    override fun save(chat: Chat): Chat {
        val chatEntity = ChatMapper.toEntity(chat)
        val savedChatEntity = chatJpaRepository.save(chatEntity)

        return ChatMapper.toDomain(savedChatEntity)
    }

    @Transactional(readOnly = true)
    override fun findByChattingRoomId(chattingRoomId: Long): List<Chat> {
        return chatJpaRepository.findByChattingRoomId(chattingRoomId)
            .map { ChatMapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun findById(chatId: Long): Chat? {
        return chatJpaRepository.findByIdOrNull(chatId)
            ?.let { ChatMapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun findLastChatByChattingRoomId(chattingRoomId: Long): Chat? {
        return chatJpaRepository.findLastChatByChattingRoomId(chattingRoomId)
            ?.let { ChatMapper.toDomain(it) }
    }
}
