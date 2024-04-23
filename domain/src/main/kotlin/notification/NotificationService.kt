package notification

import chatting.ChattingRoom
import member.Member
import post.Post

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
