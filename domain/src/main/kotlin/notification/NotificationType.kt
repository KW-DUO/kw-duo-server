package notification

import member.Member

enum class NotificationType(
    private val messageGenerator: (Member) -> String,
) {
    TEAM_INVITATION({ member -> "${member.nickname}님이 초대 요청을 보냈습니다." }),
    TEAM_JOIN({ member -> "${member.nickname}님이 가입 요청을 보냈습니다." }),
    ;

    fun createMessage(member: Member) = messageGenerator(member)
}