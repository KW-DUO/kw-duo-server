package kwduo.chatting.schema

import java.time.LocalDateTime

data class ChatSchema(
    val id: Long,
    val message: String,
    val member: ChatMemberSchema,
    val createdAt: LocalDateTime,
)
