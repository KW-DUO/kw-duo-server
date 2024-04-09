package chatting

import member.Member
import post.FindTeammatePost
import post.Post

class ChattingService(
    private val chatRepository: ChatRepository,
    private val chattingRoomRepository: ChattingRoomRepository,
) {
    fun createOrGetChattingRoom(
        teamJoiner: Member,
        teamOwner: Member
    ): ChattingRoom {
        return chattingRoomRepository.findByParticipantMemberId(teamJoiner.id!!, teamOwner.id!!)
            ?: return createChattingRoom(teamJoiner, teamOwner)
    }

    private fun createChattingRoom(
        teamJoiner: Member,
        teamOwner: Member
    ): ChattingRoom {
        val chattingRoom = ChattingRoom.create(teamJoiner.id!!, teamOwner.id!!)
        chattingRoomRepository.save(chattingRoom)

        return chattingRoom
    }

    fun sendChat(
        chattingRoom: ChattingRoom,
        sendMemberId: Long,
        message: String
    ) {
        check(chattingRoom.isMember(sendMemberId)) { "채팅방에 속한 멤버가 아닙니다." }

        val chat = Chat(
            chattingRoomId = chattingRoom.id!!,
            message = message,
            sendMemberId = sendMemberId
        )

        chatRepository.save(chat)

        chattingRoom.updateLastReadMessageTime(sendMemberId)
        chattingRoomRepository.save(chattingRoom)
    }

    fun sendFirstMetChat(post: FindTeammatePost, chattingRoom: ChattingRoom, teamJoiner: Member) {
        val chat = Chat.createFirstMetChat(post, chattingRoom, teamJoiner)
        chatRepository.save(chat)
    }
}
