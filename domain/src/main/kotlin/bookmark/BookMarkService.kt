package bookmark

class BookMarkService(
    private val bookMarkRepository: BookMarkRepository,
) {
    fun createBookMark(requestMemberId: Long, postId: Long) {
        val bookmark = bookMarkRepository.findByUserIdAndPostId(requestMemberId, postId)

        if (bookmark != null) {
            bookmark.bookmark()
            bookMarkRepository.save(bookmark)
            return
        }

        val bookmark2 = BookMark(
            userId = requestMemberId,
            postId = postId,
            isBookMarked = true,
        )

        bookMarkRepository.save(bookmark2)
    }

    fun deleteBookMark(requestMemberId: Long, postId: Long) {
        val bookmark = bookMarkRepository.findByUserIdAndPostId(requestMemberId, postId)

        if (bookmark != null) {
            bookmark.delete()
            bookMarkRepository.save(bookmark)
        }
    }
}