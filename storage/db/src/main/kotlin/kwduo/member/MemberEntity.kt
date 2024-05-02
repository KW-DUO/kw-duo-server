package kwduo.member

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import member.BaekjoonTier
import member.Department
import member.Position
import java.time.LocalDateTime

@Table(name = "member")
@Entity
class MemberEntity(
    @Column(name = "oauth_id", nullable = false)
    val oAuthId: String,
    @Column(name = "profile_img_id", nullable = true)
    var profileImgId: Long?,
    @Column(name = "nickname", nullable = false)
    var nickname: String,
    @Column(name = "bio", nullable = false)
    var bio: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    var department: Department,
//    @Column(name = "tech_stack", nullable = false)
//    var techStack: List<String>,
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    var position: Position,
    @Column(name = "email", nullable = false)
    var email: String,
    var isAuthenticated: Boolean = false,
    @Column(name = "github_url", nullable = true)
    var githubUrl: String?,
    @Column(name = "baekjoon_id", nullable = true)
    var baekjoonId: String?,
    @Enumerated(EnumType.STRING)
    var tier: BaekjoonTier? = null,
    @Column(name = "tier_updated_at", nullable = false)
    var tierUpdatedAt: LocalDateTime? = LocalDateTime.now(),
) : BaseEntity()
