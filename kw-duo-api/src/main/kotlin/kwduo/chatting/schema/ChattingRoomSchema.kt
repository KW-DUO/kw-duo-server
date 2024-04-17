package kwduo.chatting.schema

data class ChattingRoomSchema(
    val id: Long,
    val member: ChatMemberSchema,
    val lastChat: ChatSchema,
)
