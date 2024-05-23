package kwduo.chatting

import kwduo.chatting.schema.ChatMemberSchema
import kwduo.chatting.schema.ChatSchema
import kwduo.chatting.schema.ChattingRoomSchema
import java.time.LocalDateTime

object DummyChat {
    val chattingRooms =
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
        )

    val chats =
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
        )
}
