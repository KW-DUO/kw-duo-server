package kwduo.chatting

import chatting.Chat
import org.springframework.stereotype.Component

@Component
class ChatMapper {
    fun toEntity(chat: Chat) =
        ChatEntity(
            id = chat.id,
            chattingRoomId = chat.chattingRoomId,
            sendMemberId = chat.sendMemberId,
            message = chat.message,
            chatCreatedAt = chat.createdAt,
        )

    fun toDomain(chatEntity: ChatEntity) =
        Chat(
            id = chatEntity.id,
            chattingRoomId = chatEntity.chattingRoomId,
            sendMemberId = chatEntity.sendMemberId,
            message = chatEntity.message,
            createdAt = chatEntity.chatCreatedAt,
        )
}
