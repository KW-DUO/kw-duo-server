package kwduo.notification

import kwduo.chatting.ChattingRoom
import kwduo.member.Member
import kwduo.post.Post

class NotificationService(
    private val notificationRepository: NotificationRepository,
) {
    fun sendNotification(
        notificationType: NotificationType,
        requestMember: Member,
        member: Member,
        post: Post,
        chattingRoom: ChattingRoom,
    ) {
        val notification =
            Notification.create(
                notificationType,
                requestMember,
                member,
                post,
                chattingRoom,
            )

        notificationRepository.save(notification)
    }
}
