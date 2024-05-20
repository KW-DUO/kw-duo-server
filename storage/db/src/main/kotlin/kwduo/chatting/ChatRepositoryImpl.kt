package kwduo.chatting

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ChatRepositoryImpl(
    private val chatJpaRepository: ChatJpaRepository,
    private val chatMapper: ChatMapper,
) : ChatRepository {
    @Transactional
    override fun save(chat: Chat): Chat {
        val chatEntity = chatMapper.toEntity(chat)
        val savedChatEntity = chatJpaRepository.save(chatEntity)

        return chatMapper.toDomain(savedChatEntity)
    }

    @Transactional(readOnly = true)
    override fun findByChattingRoomId(chattingRoomId: Long): List<Chat> {
        return chatJpaRepository.findByChattingRoomId(chattingRoomId)
            .map { chatMapper.toDomain(it) }
    }
}
