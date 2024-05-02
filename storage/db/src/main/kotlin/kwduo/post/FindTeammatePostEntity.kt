package kwduo.post

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import post.ProjectType

@DiscriminatorValue("FIND_TEAMMATE")
@Entity
class FindTeammatePostEntity(
    title: String,
    content: String,
    authorId: Long,
    projectType: ProjectType,
    @Column(name = "recruit_number", nullable = false)
    var recruitNumber: Int,
    isDeleted: Boolean = false,
    isClosed: Boolean = false,
) : PostEntity(
        title,
        content,
        authorId,
        projectType,
        isDeleted,
        isClosed,
    )
