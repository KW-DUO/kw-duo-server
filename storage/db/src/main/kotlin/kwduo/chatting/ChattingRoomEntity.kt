package kwduo.chatting

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity
import java.time.LocalDateTime

@Table(name = "chatting_room")
@Entity
class ChattingRoomEntity(
    id: Long?,
    @Column(name = "member1_id", nullable = false)
    var member1Id: Long,
    @Column(name = "member2_id", nullable = false)
    var member2Id: Long,
    @Column(name = "member1_last_read_message_time", nullable = false)
    var member1LastReadMessageTime: LocalDateTime,
    @Column(name = "member2_last_read_message_time", nullable = false)
    var member2LastReadMessageTime: LocalDateTime,
) : BaseEntity(id)
