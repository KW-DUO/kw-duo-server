package kwduo.member

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kwduo.BaseEntity
import java.time.LocalDateTime

@Table(name = "member")
@Entity
class MemberEntity(
    id: Long? = null,
    @Column(name = "oauth_id", nullable = false)
    val oAuthId: String,
    @Column(name = "nickname", nullable = false)
    var nickname: String,
    @Column(name = "bio", nullable = false)
    var bio: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    var department: Department,
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    var position: Position,
    @Column(name = "coding_test_language", nullable = false)
    var codingTestLanguage: String,
    @Column(name = "email", nullable = false)
    var email: String,
    @Column(name = "is_authenticated", nullable = false)
    var isAuthenticated: Boolean = false,
    @Column(name = "github_url", nullable = true)
    var githubUrl: String?,
    @Column(name = "baekjoon_id", nullable = true)
    var baekjoonId: String?,
    @Enumerated(EnumType.STRING)
    var tier: BaekjoonTier? = null,
    @Column(name = "tier_updated_at", nullable = true)
    var tierUpdatedAt: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "join_at", nullable = false)
    var joinAt: LocalDateTime,
) : BaseEntity(id)
