package kwduo.chatting.dto

import kwduo.chatting.schema.ChattingRoomSchema

data class ChattingRoomResponseDTO(
    val room: List<ChattingRoomSchema>,
) {
    companion object {
        fun of(room: List<ChattingRoomInfo>): ChattingRoomResponseDTO {
            return ChattingRoomResponseDTO(room.map { ChattingRoomSchema(it) })
        }
    }
}
