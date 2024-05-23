package kwduo.bookmark

import org.springframework.stereotype.Service

@Service
class BookmarkService(
    private val bookmarkRepository: BookmarkRepository,
) {
    fun createBookMark(
        requestMemberId: Long,
        postId: Long,
    ) {
        val bookmark = bookmarkRepository.findByUserIdAndPostId(requestMemberId, postId)

        if (bookmark != null) {
            bookmark.bookmark()
            bookmarkRepository.save(bookmark)
            return
        }

        val newBookmark =
            Bookmark(
                memberId = requestMemberId,
                postId = postId,
                isBookMarked = true,
            )

        bookmarkRepository.save(newBookmark)
    }

    fun deleteBookMark(
        requestMemberId: Long,
        postId: Long,
    ) {
        val bookmark = bookmarkRepository.findByUserIdAndPostId(requestMemberId, postId)

        if (bookmark != null) {
            bookmark.delete(requestMemberId)
            bookmarkRepository.save(bookmark)
        }
    }
}
