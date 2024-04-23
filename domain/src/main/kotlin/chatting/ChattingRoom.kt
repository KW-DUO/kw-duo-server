package chatting

import java.time.LocalDateTime

class ChattingRoom(
    val id: Long? = null,
    val member1Id: Long,
    val member2Id: Long,
    var member1LastReadMessageTime: LocalDateTime,
    var member2LastReadMessageTime: LocalDateTime,
) {
    fun isMember(id: Long) = id == member1Id || id == member2Id

    fun updateLastReadMessageTime(
        memberId: Long,
        now: LocalDateTime = LocalDateTime.now(),
    ) {
        check(!isMember(memberId)) { "채팅방에 속한 멤버가 아닙니다." }

        if (member1Id == memberId) {
            member1LastReadMessageTime = now
        } else {
            member2LastReadMessageTime = now
        }
    }

    companion object {
        fun create(
            teamJoinerId: Long,
            teamOwnerId: Long,
            now: LocalDateTime = LocalDateTime.now(),
        ) = ChattingRoom(
            member1Id = teamJoinerId,
            member2Id = teamOwnerId,
            member1LastReadMessageTime = now,
            member2LastReadMessageTime = now,
        )
    }
}
