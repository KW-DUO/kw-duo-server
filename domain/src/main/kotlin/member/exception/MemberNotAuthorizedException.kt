package member.exception

class MemberNotAuthorizedException(message: String = "권한이 없습니다.") : RuntimeException(message)
