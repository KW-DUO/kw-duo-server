package kwduo.chatting.dto

import java.time.LocalDateTime

data class ChatInfo(
    val id: Long,
    val message: String,
    val member: ChatMemberInfo,
    val createdAt: LocalDateTime,
)
