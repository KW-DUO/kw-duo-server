package kwduo.apply

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity

@Table(name = "apply")
@Entity
class ApplyEntity(
    var memberId: Long,
    var postId: Long,
) : BaseEntity()
