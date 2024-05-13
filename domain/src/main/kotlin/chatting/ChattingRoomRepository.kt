package chatting

interface ChattingRoomRepository {
    fun save(chattingRoom: ChattingRoom): ChattingRoom

    fun findById(id: Long): ChattingRoom?

    fun findByParticipantMembersId(
        member1Id: Long,
        member2Id: Long,
    ): ChattingRoom?

    fun findByParticipantMemberId(memberId: Long): List<ChattingRoom>
}
