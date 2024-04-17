package kwduo.chatting.schema

import java.time.LocalDateTime

data class ChattingRoomSchema(
    val id: Long,
    val member: ChatMemberSchema,
    val lastChat: ChatSchema,
)
