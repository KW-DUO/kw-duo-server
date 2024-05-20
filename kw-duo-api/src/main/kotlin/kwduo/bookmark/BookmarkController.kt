package kwduo.bookmark

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Bookmark")
@RestController
class BookmarkController(
    private val bookmarkService: BookmarkService,
) {
    @Operation(summary = "북마크 추가")
    @PostMapping("/bookmarks/{postId}")
    fun addBookmark(
        @PathVariable postId: Long,
    ) {
        val requestMemberId = 1L

        bookmarkService.createBookMark(requestMemberId, postId)
    }

    @Operation(summary = "북마크 삭제")
    @DeleteMapping("/bookmarks/{postId}")
    fun deleteBookmark(
        @PathVariable postId: Long,
    ) {
        val requestMemberId = 1L

        bookmarkService.deleteBookMark(requestMemberId, postId)
    }
}
