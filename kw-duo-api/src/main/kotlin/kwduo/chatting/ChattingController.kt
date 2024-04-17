package kwduo.chatting

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
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

@RestController
class ChattingController {
    @GetMapping("/chats")
    fun getChattingRoomList(
        @RequestParam(name = "q", required = false) nickname: String?,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0") page: Int,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ChattingRoomResponseDTO {
        if (nickname == "i dont want see") {
            return ChattingRoomResponseDTO(room = emptyList(), hasMore = false)
        }

        return ChattingRoomResponseDTO(
            room = listOf(
                ChattingRoomSchema(
                    id = 1,
                    ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                    lastChat = ChatSchema(
                        1,
                        "안녕하세요",
                        ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                        LocalDateTime.now()
                    ),
                ),
                ChattingRoomSchema(
                    id = 2,
                    member = ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "Ruby1"),
                    lastChat = ChatSchema(
                        2,
                        "중간고사 족보 삽니다",
                        ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "Ruby1"),
                        LocalDateTime.now().minusDays(2)
                    ),
                ),
            ),
            hasMore = false
        )
    }

    @GetMapping("/chats/{roomId}")
    fun getChats(
        @PathVariable roomId: Long,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0") page: Int,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ChatResponseDTO {
        return ChatResponseDTO(
            listOf(
                ChatSchema(
                    1,
                    "안녕하세요",
                    ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                    LocalDateTime.now().minusDays(3)
                ),
                ChatSchema(
                    2,
                    "안녕하세요",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "Ruby1"),
                    LocalDateTime.now().minusDays(3)
                ),
                ChatSchema(
                    3,
                    "족보 삽니다",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "Ruby1"),
                    LocalDateTime.now().minusDays(3)
                ),
                ChatSchema(
                    4,
                    "족발 보쌈이요?",
                    ChatMemberSchema(1, "김개발", "https://avatars.githubusercontent.com/u/12345678?v=4", null),
                    LocalDateTime.now().minusDays(2)
                ),
                ChatSchema(
                    5,
                    "? ;;;",
                    ChatMemberSchema(2, "Faker", "https://avatars.githubusercontent.com/u/12345678?v=4", "Ruby1"),
                    LocalDateTime.now()
                ),
            ),
        )
    }
}