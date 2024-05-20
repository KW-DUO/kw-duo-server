package kwduo.bookmark

interface BookMarkRepository {
    fun save(bookMark: kwduo.bookmark.BookMark): kwduo.bookmark.BookMark

    fun findByUserIdAndPostId(
        requestMemberId: Long,
        postId: Long,
    ): kwduo.bookmark.BookMark?
}
