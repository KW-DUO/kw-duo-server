package kwduo.bookmark

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class BookmarkRepositoryImpl(
    private val bookmarkMapper: BookmarkMapper,
    private val bookmarkJpaRepository: BookmarkJpaRepository,
) : BookmarkRepository {
    @Transactional
    override fun save(bookMark: Bookmark): Bookmark {
        val bookmarkEntity = bookmarkMapper.toEntity(bookMark)
        val savedEntity = bookmarkJpaRepository.save(bookmarkEntity)

        return bookmarkMapper.toDomain(savedEntity)
    }

    @Transactional(readOnly = true)
    override fun findByUserIdAndPostId(
        requestMemberId: Long,
        postId: Long,
    ): Bookmark? {
        return bookmarkJpaRepository.findByMemberIdAndPostId(requestMemberId, postId)
            ?.let { bookmarkMapper.toDomain(it) }
    }
}
