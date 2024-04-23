package chatting

import member.Member
import post.FindTeammatePost
import java.time.LocalDateTime

class Chat(
    val id: Long? = null,
    val chattingRoomId: Long,
    val sendMemberId: Long,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(message.isNotBlank()) { "메시지는 공백일 수 없습니다." }
    }

    companion object {
        fun createFirstMetChat(
            post: FindTeammatePost,
            chattingRoom: ChattingRoom,
            teamJoiner: Member,
        ) = Chat(
            chattingRoomId = chattingRoom.id!!,
            message =
                "안녕하세요! ${post.getSimplifiedTitle(10)} 팀 프로젝트에 지원하고 싶습니다 :)\n" +
                    "저의 기본정보는 아래와 같습니다!\n" +
                    "닉네임: ${teamJoiner.nickname}\n" +
                    "학과: ${teamJoiner.department.displayName}\n" +
                    "지원 포지션: ${teamJoiner.position.displayName}\n" +
                    "기술 스택: ${teamJoiner.techStackNames.joinToString(", ")}",
            sendMemberId = teamJoiner.id!!,
        )
    }
}
