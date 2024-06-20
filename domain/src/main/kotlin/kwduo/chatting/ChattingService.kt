package kwduo.chatting

import kwduo.chatting.dto.ChatInfo
import kwduo.chatting.dto.ChatMemberInfo
import kwduo.chatting.dto.ChattingRoomInfo
import kwduo.member.Member
import kwduo.member.MemberReader
import kwduo.post.FindTeammatePost
import org.springframework.stereotype.Service

@Service
class ChattingService(
    private val memberReader: MemberReader,
    private val chatRepository: ChatRepository,
    private val chattingRoomRepository: ChattingRoomRepository,
) {
    fun getAllChattingRoom(memberId: Long): List<ChattingRoomInfo> {
        val chattingRooms = chattingRoomRepository.findByParticipantMemberId(memberId)

        return chattingRooms.map {
            getChattingRoomInfo(it, memberId)
        }
    }

    private fun getChattingRoomInfo(
        chattingRoom: ChattingRoom,
        memberId: Long,
    ): ChattingRoomInfo {
        val member = memberReader.findById(chattingRoom.getOtherParticipantId(memberId))

        val lastChat = chatRepository.findLastChatByChattingRoomId(chattingRoom.id!!)
        lastChat ?: return ChattingRoomInfo(chattingRoom.id, member)

        val lastChatMember = memberReader.findById(lastChat.sendMemberId)

        return ChattingRoomInfo(
            id = chattingRoom.id,
            member = member,
            lastChat = lastChat,
            lastChatMember = lastChatMember,
        )
    }

    fun createOrGetChattingRoom(
        teamJoiner: Member,
        teamOwner: Member,
    ): ChattingRoom {
        return chattingRoomRepository.findByParticipantMembersId(teamJoiner.id!!, teamOwner.id!!)
            ?: return createChattingRoom(teamJoiner, teamOwner)
    }

    private fun createChattingRoom(
        teamJoiner: Member,
        teamOwner: Member,
    ): ChattingRoom {
        val chattingRoom = ChattingRoom.create(teamJoiner.id!!, teamOwner.id!!)
        chattingRoomRepository.save(chattingRoom)

        return chattingRoom
    }

    fun sendChat(
        chattingRoomId: Long,
        sendMemberId: Long,
        message: String,
    ): Chat {
        val chattingRoom =
            chattingRoomRepository.findById(chattingRoomId)
                ?: throw IllegalArgumentException("채팅방을 찾을 수 없습니다.")
        check(chattingRoom.isMember(sendMemberId)) { "채팅방에 속한 멤버가 아닙니다." }

        val chat =
            Chat(
                chattingRoomId = chattingRoom.id!!,
                message = message,
                sendMemberId = sendMemberId,
            )

        val savedChat = chatRepository.save(chat)

        chattingRoom.updateLastReadMessageTime(sendMemberId)
        chattingRoomRepository.save(chattingRoom)

        return savedChat
    }

    fun sendFirstMetChat(
        post: FindTeammatePost,
        chattingRoom: ChattingRoom,
        teamJoiner: Member,
    ) {
        val chat = Chat.createFirstMetChat(post, chattingRoom, teamJoiner)
        chatRepository.save(chat)
    }

    fun getChatsByRoomId(
        requestMemberId: Long,
        roomId: Long,
    ): List<ChatInfo> {
        val chattingRoom =
            chattingRoomRepository.findById(roomId)
                ?: throw IllegalArgumentException("채팅방을 찾을 수 없습니다.")

        check(chattingRoom.isMember(requestMemberId)) { "채팅방에 속한 멤버가 아닙니다." }

        return chatRepository.findByChattingRoomId(roomId)
            .map {
                val member = memberReader.findById(it.sendMemberId)
                ChatInfo(
                    id = it.id!!,
                    message = it.message,
                    member =
                        ChatMemberInfo(
                            id = member.id!!,
                            nickname = member.nickname,
                            baekjoonTier = member.baekjoonInfo?.tier,
                        ),
                    createdAt = it.createdAt,
                )
            }
    }
}
