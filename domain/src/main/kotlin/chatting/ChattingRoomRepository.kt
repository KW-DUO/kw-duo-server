package chatting

interface ChattingRoomRepository {
    fun save(chattingRoom: ChattingRoom): ChattingRoom

    fun findById(id: Long): ChattingRoom?

    fun findByParticipantMemberId(
        member1Id: Long,
        member2Id: Long,
    ): ChattingRoom?

    fun findChattingRoomByParticipantMemberId(memberId: Long): List<ChattingRoom>
}
