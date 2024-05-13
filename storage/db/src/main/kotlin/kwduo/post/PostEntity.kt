package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table
import kwduo.BaseEntity

@DiscriminatorColumn(name = "post_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "post")
@Entity
abstract class PostEntity(
    @Column(name = "title", nullable = false, length = 100)
    var title: String,
    @Column(name = "content", nullable = false)
    var content: String,
    @Column(name = "author_id", nullable = false)
    var authorId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    var projectType: ProjectType,
    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false,
    @Column(name = "is_closed", nullable = false)
    var isClosed: Boolean = false,
) : BaseEntity()
