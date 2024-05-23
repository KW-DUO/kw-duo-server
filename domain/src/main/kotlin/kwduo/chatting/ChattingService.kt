package kwduo.chatting

import kwduo.chatting.dto.ChattingRoomInfo
import kwduo.image.ImageReader
import kwduo.member.Member
import kwduo.member.MemberReader
import kwduo.post.FindTeammatePost
import org.springframework.stereotype.Service

@Service
class ChattingService(
    private val memberReader: MemberReader,
    private val imageReader: ImageReader,
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
        val memberProfileImg = member.profileImgId?.let { imageReader.findById(it) }

        val lastChat = chatRepository.findLastChatByChattingRoomId(chattingRoom.id!!)

        lastChat ?: return ChattingRoomInfo(
            chattingRoom.id,
            member,
            memberProfileImg,
        )

        val lastChatMember = memberReader.findById(lastChat.sendMemberId)
        val lastChatMemberProfileImg = imageReader.findById(lastChatMember.id!!)

        return ChattingRoomInfo(
            id = chattingRoom.id,
            member = member,
            memberProfileImg = memberProfileImg,
            lastChat = lastChat,
            lastChatMember = lastChatMember,
            lastChatMemberProfileImg = lastChatMemberProfileImg,
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
        chattingRoom: ChattingRoom,
        sendMemberId: Long,
        message: String,
    ) {
        check(chattingRoom.isMember(sendMemberId)) { "채팅방에 속한 멤버가 아닙니다." }

        val chat =
            Chat(
                chattingRoomId = chattingRoom.id!!,
                message = message,
                sendMemberId = sendMemberId,
            )

        chatRepository.save(chat)

        chattingRoom.updateLastReadMessageTime(sendMemberId)
        chattingRoomRepository.save(chattingRoom)
    }

    fun sendFirstMetChat(
        post: FindTeammatePost,
        chattingRoom: ChattingRoom,
        teamJoiner: Member,
    ) {
        val chat = Chat.createFirstMetChat(post, chattingRoom, teamJoiner)
        chatRepository.save(chat)
    }
}
