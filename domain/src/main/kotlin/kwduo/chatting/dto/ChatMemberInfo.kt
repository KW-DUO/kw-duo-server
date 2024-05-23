package kwduo.chatting.dto

import kwduo.member.BaekjoonTier

data class ChatMemberInfo(
    val id: Long,
    val nickname: String,
    val profileImgUrl: String?,
    val baekjoonTier: BaekjoonTier?,
)
