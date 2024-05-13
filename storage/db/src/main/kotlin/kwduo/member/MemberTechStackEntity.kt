package kwduo.member

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import member.TechStack

@Table(name = "member_tech_stack")
@Entity
class MemberTechStackEntity(
    @Column(name = "member_id", nullable = false)
    var memberId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "tech_stack", nullable = false)
    var techStack: TechStack,
) : BaseEntity()
