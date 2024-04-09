package notification

import chatting.ChattingRoom
import member.Member
import post.Post
import java.time.LocalDateTime

class Notification(
    val id: Long? = null,
    val type: NotificationType,
    val content: String,
    val memberId: Long,
    val postId: Long,
    val chattingRoomId: Long,
    var isRead: Boolean = false,
    var isDeleted: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    fun read(member: Member) {
        check(member.id != memberId) { "다른 멤버의 알림을 읽을 수 없습니다" }
        check(!isDeleted) { "삭제된 알림입니다" }
        isRead = true
    }

    fun delete(member: Member) {
        check(member.id != memberId) { "다른 멤버의 알림을 삭제할 수 없습니다" }
        isDeleted = true
    }

    companion object {
        fun create(
            type: NotificationType,
            requestMember: Member,
            member: Member,
            post: Post,
            chattingRoom: ChattingRoom,
        ) = Notification(
            type = type,
            content = type.createMessage(requestMember),
            memberId = member.id!!,
            postId = post.id!!,
            chattingRoomId = chattingRoom.id!!,
        )
    }
}