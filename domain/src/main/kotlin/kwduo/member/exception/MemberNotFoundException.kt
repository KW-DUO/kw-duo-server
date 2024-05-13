package kwduo.member.exception

class MemberNotFoundException(message: String = "멤버를 찾을 수 없습니다.") : RuntimeException(message)
