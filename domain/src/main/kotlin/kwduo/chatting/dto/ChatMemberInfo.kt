package kwduo.chatting.dto

import kwduo.member.BaekjoonTier

data class ChatMemberInfo(
    val id: Long,
    val nickname: String,
    val baekjoonTier: BaekjoonTier?,
)
