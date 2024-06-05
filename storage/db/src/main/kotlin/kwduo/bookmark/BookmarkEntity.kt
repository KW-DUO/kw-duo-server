package kwduo.bookmark

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity

@Table(name = "bookmark")
@Entity
class BookmarkEntity(
    id: Long?,
    @Column(name = "member_id", nullable = false)
    var memberId: Long,
    @Column(name = "post_id", nullable = false)
    var postId: Long,
    @Column(name = "is_bookmarked", nullable = false)
    var isBookMarked: Boolean,
) : BaseEntity(id)
