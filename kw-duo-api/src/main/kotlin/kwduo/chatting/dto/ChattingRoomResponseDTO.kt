package kwduo.chatting.dto

import kwduo.chatting.schema.ChattingRoomSchema

data class ChattingRoomResponseDTO(
    val room: List<ChattingRoomSchema>,
    val hasMore: Boolean,
)
