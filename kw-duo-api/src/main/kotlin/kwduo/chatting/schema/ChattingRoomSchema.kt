package kwduo.chatting.schema

import kwduo.chatting.dto.ChattingRoomInfo

data class ChattingRoomSchema(
    val id: Long,
    val member: ChatMemberSchema,
    val lastChat: ChatSchema?,
) {
    constructor(info: ChattingRoomInfo) : this(
        id = info.id,
        member = ChatMemberSchema(info.member),
        lastChat = info.lastChat?.let { ChatSchema(it) },
    )
}
