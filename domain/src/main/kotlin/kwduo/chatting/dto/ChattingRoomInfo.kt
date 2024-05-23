package kwduo.chatting.dto

import kwduo.chatting.Chat
import kwduo.image.Image
import kwduo.member.Member

data class ChattingRoomInfo(
    val id: Long,
    val member: ChatMemberInfo,
    val lastChat: ChatInfo?,
) {
    constructor(
        id: Long,
        member: Member,
        memberProfileImg: Image?,
        lastChat: Chat,
        lastChatMember: Member,
        lastChatMemberProfileImg: Image?,
    ) : this(
        id = id,
        member =
            ChatMemberInfo(
                id = member.id!!,
                nickname = member.nickname,
                profileImgUrl = memberProfileImg?.url,
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
                        profileImgUrl = lastChatMemberProfileImg?.url,
                        baekjoonTier = lastChatMember.baekjoonInfo?.tier,
                    ),
                createdAt = lastChat.createdAt,
            ),
    )

    constructor(
        id: Long,
        member: Member,
        memberProfileImg: Image?,
    ) : this(
        id,
        ChatMemberInfo(
            id = member.id!!,
            nickname = member.nickname,
            profileImgUrl = memberProfileImg?.url,
            baekjoonTier = member.baekjoonInfo?.tier,
        ),
        null,
    )
}
