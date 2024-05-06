package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kwduo.BaseEntity

@Table(name = "post_field")
@Entity
class PostFieldEntity(
    @Column(name = "post_id", nullable = false)
    var postId: Long,
    @Column(name = "field_id", nullable = false)
    var fieldId: Long,
) : BaseEntity()
