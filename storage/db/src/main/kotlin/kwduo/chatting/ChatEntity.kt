package kwduo.chatting

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity
import java.time.LocalDateTime

@Table(name = "chat")
@Entity
class ChatEntity(
    @Column(name = "chatting_room_id", nullable = false)
    var chattingRoomId: Long,
    @Column(name = "send_member_id", nullable = false)
    var sendMemberId: Long,
    @Column(name = "message", nullable = false)
    var message: String,
    @Column(name = "chat_created_at", nullable = false, updatable = false)
    var chatCreatedAt: LocalDateTime,
) : BaseEntity()
