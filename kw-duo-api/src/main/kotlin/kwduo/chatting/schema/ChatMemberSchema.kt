package kwduo.chatting.schema

import kwduo.chatting.dto.ChatMemberInfo

data class ChatMemberSchema(
    val id: Long,
    val nickname: String,
    val profileImgUrl: String?,
    val baekjoonTier: String?,
) {
    constructor(info: ChatMemberInfo) : this(
        id = info.id,
        nickname = info.nickname,
        profileImgUrl = info.profileImgUrl,
        baekjoonTier = info.baekjoonTier?.value,
    )
}
