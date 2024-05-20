package kwduo.post.exception

class PostNotFoundException(message: String = "멤버를 찾을 수 없습니다.") : RuntimeException(message)
