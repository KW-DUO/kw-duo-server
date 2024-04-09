package post

data class PostDetail(
    val title: String,
    val content: String,
) {
    init {
        require(title.isNotBlank()) { "제목은 필수입니다." }
        require(content.isNotBlank()) { "내용은 필수입니다." }
    }
}
