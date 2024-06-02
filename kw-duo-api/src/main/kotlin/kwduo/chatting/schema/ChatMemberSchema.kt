package kwduo.chatting.schema

import kwduo.chatting.dto.ChatMemberInfo

data class ChatMemberSchema(
    val id: Long,
    val nickname: String,
    val baekjoonTier: String?,
) {
    constructor(info: ChatMemberInfo) : this(
        id = info.id,
        nickname = info.nickname,
        baekjoonTier = info.baekjoonTier?.value,
    )
}
