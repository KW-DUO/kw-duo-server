package kwduo.chatting

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ChattingRoomRepositoryImpl(
    private val chattingRoomJpaRepository: ChattingRoomJpaRepository,
) : ChattingRoomRepository {
    override fun save(chattingRoom: ChattingRoom): ChattingRoom {
        val chattingRoomEntity = ChattingRoomMapper.toEntity(chattingRoom)
        val savedChattingRoomEntity = chattingRoomJpaRepository.save(chattingRoomEntity)

        return ChattingRoomMapper.toDomain(savedChattingRoomEntity)
    }

    override fun findById(id: Long): ChattingRoom? {
        return chattingRoomJpaRepository.findByIdOrNull(id)
            ?.let { ChattingRoomMapper.toDomain(it) }
    }

    override fun findByParticipantMembersId(
        member1Id: Long,
        member2Id: Long,
    ): ChattingRoom? {
        return chattingRoomJpaRepository.findByParticipantMembersId(member1Id, member2Id)
            ?.let { ChattingRoomMapper.toDomain(it) }
    }

    override fun findByParticipantMemberId(memberId: Long): List<ChattingRoom> {
        return chattingRoomJpaRepository.findByParticipantMemberId(memberId)
            .map { ChattingRoomMapper.toDomain(it) }
    }
}
