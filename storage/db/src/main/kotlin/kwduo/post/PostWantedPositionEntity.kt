package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import kwduo.member.Position

@Table(name = "post_wanted_position")
@Entity
class PostWantedPositionEntity(
    @Column(name = "post_id", nullable = false)
    var postId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    var position: Position,
) : BaseEntity()
