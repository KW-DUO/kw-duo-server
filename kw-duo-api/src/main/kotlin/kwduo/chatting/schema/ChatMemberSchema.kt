package kwduo.chatting.schema

data class ChatMemberSchema(
    val id: Long,
    val nickname: String,
    val profileImgUrl: String?,
    val baekjoonTier: String?,
)
