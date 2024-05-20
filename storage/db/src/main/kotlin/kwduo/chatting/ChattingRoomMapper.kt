package kwduo.chatting

object ChattingRoomMapper {
    fun toEntity(chattingRoom: ChattingRoom) =
        ChattingRoomEntity(
            id = chattingRoom.id,
            member1Id = chattingRoom.member1Id,
            member2Id = chattingRoom.member2Id,
            member1LastReadMessageTime = chattingRoom.member1LastReadMessageTime,
            member2LastReadMessageTime = chattingRoom.member2LastReadMessageTime,
        )

    fun toDomain(chattingRoomEntity: ChattingRoomEntity) =
        ChattingRoom(
            id = chattingRoomEntity.id,
            member1Id = chattingRoomEntity.member1Id,
            member2Id = chattingRoomEntity.member2Id,
            member1LastReadMessageTime = chattingRoomEntity.member1LastReadMessageTime,
            member2LastReadMessageTime = chattingRoomEntity.member2LastReadMessageTime,
        )
}
