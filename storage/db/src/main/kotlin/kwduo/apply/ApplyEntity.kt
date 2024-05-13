package kwduo.apply

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity

@Table(name = "apply")
@Entity
class ApplyEntity(
    id: Long?,
    @Column(name = "member_id", nullable = false)
    var memberId: Long,
    @Column(name = "post_id", nullable = false)
    var postId: Long,
) : BaseEntity(id)
