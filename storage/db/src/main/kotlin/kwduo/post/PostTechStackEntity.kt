package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import member.TechStack

@Table(name = "post_tech_stack")
@Entity
class PostTechStackEntity(
    @Column(name = "post_id", nullable = false)
    var postId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "tech_stack", nullable = false)
    var techStack: TechStack,
) : BaseEntity()
