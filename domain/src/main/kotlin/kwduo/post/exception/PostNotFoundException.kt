package kwduo.post.exception

class PostNotFoundException(message: String = "아티클을 찾을 수 없습니다.") : RuntimeException(message)
