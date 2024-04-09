package notification

interface NotificationRepository {
    fun save(notification: Notification): Notification
    fun findByMemberId(memberId: Long): List<Notification>
}