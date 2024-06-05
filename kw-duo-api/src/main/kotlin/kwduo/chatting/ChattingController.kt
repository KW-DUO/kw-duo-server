package kwduo.chatting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.auth.LoggedInMemberReader
import kwduo.chatting.dto.ChatResponseDTO
import kwduo.chatting.dto.ChattingRoomResponseDTO
import kwduo.chatting.schema.ChatSchema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Chatting")
@RestController
class ChattingController(
    private val chattingService: ChattingService,
) {
    @Operation(summary = "채팅방 목록 조회")
    @GetMapping("/chats")
    fun getChattingRoomList(
        @RequestParam(name = "q", required = false) nickname: String?,
    ): ChattingRoomResponseDTO {
        if (nickname == "dummy") {
            return ChattingRoomResponseDTO(DummyChat.chattingRooms)
        }

        val chattingRooms = chattingService.getAllChattingRoom(LoggedInMemberReader.currentMemberId)
        return ChattingRoomResponseDTO.of(chattingRooms)
    }

    @Operation(summary = "채팅방의 채팅 조회")
    @GetMapping("/chats/{roomId}")
    fun getChats(
        @PathVariable roomId: Long,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ChatResponseDTO {
        val chats = chattingService.getChatsByRoomId(LoggedInMemberReader.currentMemberId, roomId)

        return ChatResponseDTO(
            chats.map { ChatSchema(it) },
        )
    }
}
