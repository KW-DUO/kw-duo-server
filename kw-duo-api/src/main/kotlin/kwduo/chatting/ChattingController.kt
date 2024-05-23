package kwduo.chatting

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.auth.LoggedInMemberReader
import kwduo.chatting.dto.ChatResponseDTO
import kwduo.chatting.dto.ChattingRoomResponseDTO
import kwduo.chatting.schema.ChatMemberSchema
import kwduo.chatting.schema.ChatSchema
import kwduo.chatting.schema.ChattingRoomSchema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

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
            return ChattingRoomResponseDTO(
                listOf(
                    ChattingRoomSchema(
                        1,
                        ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                        ChatSchema(
                            1,
                            "안녕하세요",
                            ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                            LocalDateTime.now(),
                        ),
                    ),
                    ChattingRoomSchema(
                        2,
                        ChatMemberSchema(
                            2,
                            "Faker",
                            "https://avatars.githubusercontent.com/u/12345678?v=4",
                            "RUBY1",
                        ),
                        ChatSchema(
                            2,
                            "중간고사 족보 삽니다",
                            ChatMemberSchema(
                                2,
                                "Faker",
                                "https://avatars.githubusercontent.com/u/12345678?v=4",
                                "RUBY1",
                            ),
                            LocalDateTime.now().minusDays(2),
                        ),
                    ),
                    ChattingRoomSchema(
                        3,
                        ChatMemberSchema(3, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                        null,
                    ),
                ),
            )
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
        return ChatResponseDTO(
            listOf(
                ChatSchema(
                    1,
                    "안녕하세요",
                    ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                    LocalDateTime.now().minusDays(3),
                ),
                ChatSchema(
                    2,
                    "안녕하세요",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "RUBY1"),
                    LocalDateTime.now().minusDays(3),
                ),
                ChatSchema(
                    3,
                    "족보 삽니다",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "RUBY1"),
                    LocalDateTime.now().minusDays(3),
                ),
                ChatSchema(
                    4,
                    "족발 보쌈이요?",
                    ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                    LocalDateTime.now().minusDays(2),
                ),
                ChatSchema(
                    5,
                    "? ;;;",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "RUBY1"),
                    LocalDateTime.now(),
                ),
            ),
        )
    }
}
