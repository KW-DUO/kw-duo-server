package kwduo.chatting.dto

import kwduo.chatting.Chat
import kwduo.member.Member

data class ChattingRoomInfo(
    val id: Long,
    val member: ChatMemberInfo,
    val lastChat: ChatInfo?,
) {
    constructor(
        id: Long,
        member: Member,
        lastChat: Chat,
        lastChatMember: Member,
    ) : this(
        id = id,
        member =
            ChatMemberInfo(
                id = member.id!!,
                nickname = member.nickname,
                baekjoonTier = member.baekjoonInfo?.tier,
            ),
        lastChat =
            ChatInfo(
                id = lastChat.id!!,
                message = lastChat.message,
                member =
                    ChatMemberInfo(
                        id = lastChat.id,
                        nickname = lastChatMember.nickname,
                        baekjoonTier = lastChatMember.baekjoonInfo?.tier,
                    ),
                createdAt = lastChat.createdAt,
            ),
    )

    constructor(
        id: Long,
        member: Member,
    ) : this(
        id,
        ChatMemberInfo(
            id = member.id!!,
            nickname = member.nickname,
            baekjoonTier = member.baekjoonInfo?.tier,
        ),
        null,
    )
}
