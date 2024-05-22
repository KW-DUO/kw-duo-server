package kwduo.image

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity
import java.time.LocalDateTime

@Table(name = "image")
@Entity
class ImageEntity(
    id: Long?,
    @Column(name = "url", nullable = false, updatable = false)
    var url: String,
    @Column(name = "is_used", nullable = false)
    var isUsed: Boolean,
    @Column(name = "uploaded_at", nullable = false)
    var uploadedAt: LocalDateTime = LocalDateTime.now(),
) : BaseEntity(id)
