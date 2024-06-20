package kwduo.chatting.schema

import kwduo.chatting.dto.ChatInfo
import java.time.LocalDateTime

data class ChatSchema(
    val id: Long,
    val message: String,
    val member: ChatMemberSchema,
    val createdAt: LocalDateTime,
) {
    constructor(info: ChatInfo) : this(
        id = info.id,
        message = info.message,
        member = ChatMemberSchema(info.member),
        createdAt = info.createdAt,
    )
}
