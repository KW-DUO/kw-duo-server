package kwduo.bookmark

object BookmarkMapper {
    fun toEntity(bookmark: Bookmark) =
        BookmarkEntity(
            id = bookmark.id,
            memberId = bookmark.memberId,
            postId = bookmark.postId,
            isBookMarked = bookmark.isBookMarked,
        )

    fun toDomain(bookmarkEntity: BookmarkEntity) =
        Bookmark(
            id = bookmarkEntity.id,
            memberId = bookmarkEntity.memberId,
            postId = bookmarkEntity.postId,
            isBookMarked = bookmarkEntity.isBookMarked,
        )
}
