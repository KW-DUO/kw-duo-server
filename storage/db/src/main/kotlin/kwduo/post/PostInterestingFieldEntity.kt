package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import post.Field

@Table(name = "post_interesting_field")
@Entity
class PostInterestingFieldEntity(
    @Column(name = "post_id", nullable = false)
    val postId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "field", nullable = false)
    val field: Field,
) : BaseEntity()
