package kwduo.chatting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.auth.LoggedInMemberReader
import kwduo.chatting.dto.ChatRequestDTO
import kwduo.chatting.dto.ChatResponseDTO
import kwduo.chatting.dto.ChattingRoomResponseDTO
import kwduo.chatting.schema.ChatMemberSchema
import kwduo.chatting.schema.ChatSchema
import kwduo.member.MemberReader
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Chatting")
@RestController
class ChattingController(
    private val chattingService: ChattingService,
    private val memberReader: MemberReader,
) {
    @NeedLogin
    @Operation(summary = "채팅방 목록 조회")
    @GetMapping("/chats")
    fun getChattingRoomList(
        @RequestParam(name = "q", required = false) nickname: String?,
    ): ChattingRoomResponseDTO {
        val chattingRooms = chattingService.getAllChattingRoom(LoggedInMemberReader.currentMemberId)
        return ChattingRoomResponseDTO.of(chattingRooms)
    }

    @NeedLogin
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

    @Operation(summary = "채팅방의 채팅 전송")
    @MessageMapping("/{roomId}")
    @SendTo("/chat/{roomId}")
    fun sendChat(
        @DestinationVariable roomId: Long,
        request: ChatRequestDTO,
    ): ChatSchema {
        val chat =
            chattingService.sendChat(
                roomId,
                request.requestMemberId,
                request.message,
            )

        val member = memberReader.findById(chat.sendMemberId)

        return ChatSchema(
            id = chat.id!!,
            message = chat.message,
            member =
                ChatMemberSchema(
                    id = member.id!!,
                    nickname = member.nickname,
                    baekjoonTier = member.baekjoonInfo?.tier?.value,
                ),
            createdAt = chat.createdAt,
        )
    }
}
