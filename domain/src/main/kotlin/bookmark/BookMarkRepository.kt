package bookmark

interface BookMarkRepository {
    fun save(bookMark: BookMark): BookMark

    fun findByUserIdAndPostId(
        requestMemberId: Long,
        postId: Long,
    ): BookMark?
}
