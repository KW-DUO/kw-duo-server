package kwduo.chatting

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ChattingRoomRepositoryImpl(
    private val chattingRoomJpaRepository: ChattingRoomJpaRepository,
    private val chattingRoomMapper: ChattingRoomMapper,
) : ChattingRoomRepository {
    override fun save(chattingRoom: ChattingRoom): ChattingRoom {
        val chattingRoomEntity = chattingRoomMapper.toEntity(chattingRoom)
        val savedChattingRoomEntity = chattingRoomJpaRepository.save(chattingRoomEntity)

        return chattingRoomMapper.toDomain(savedChattingRoomEntity)
    }

    override fun findById(id: Long): ChattingRoom? {
        return chattingRoomJpaRepository.findByIdOrNull(id)
            ?.let { chattingRoomMapper.toDomain(it) }
    }

    override fun findByParticipantMembersId(
        member1Id: Long,
        member2Id: Long,
    ): ChattingRoom? {
        return chattingRoomJpaRepository.findByParticipantMembersId(member1Id, member2Id)
            ?.let { chattingRoomMapper.toDomain(it) }
    }

    override fun findByParticipantMemberId(memberId: Long): List<ChattingRoom> {
        return chattingRoomJpaRepository.findByParticipantMemberId(memberId)
            .map { chattingRoomMapper.toDomain(it) }
    }
}
