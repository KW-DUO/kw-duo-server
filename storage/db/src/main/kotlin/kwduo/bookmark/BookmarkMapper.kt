package kwduo.bookmark

object BookmarkMapper {
    fun toEntity(bookmark: Bookmark) =
        BookmarkEntity(
            memberId = bookmark.memberId,
            postId = bookmark.postId,
            isBookMarked = bookmark.isBookMarked,
        )

    fun toDomain(bookmarkEntity: BookmarkEntity) =
        Bookmark(
            memberId = bookmarkEntity.memberId,
            postId = bookmarkEntity.postId,
            isBookMarked = bookmarkEntity.isBookMarked,
        )
}
