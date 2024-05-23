package kwduo.bookmark

interface BookmarkRepository {
    fun save(bookMark: Bookmark): Bookmark

    fun findByUserIdAndPostId(
        requestMemberId: Long,
        postId: Long,
    ): Bookmark?
}
