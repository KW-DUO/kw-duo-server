package post

import member.Member
import member.MemberNotAuthorizedException
import member.Position
import java.time.LocalDateTime

abstract class Post(
    val id: Long? = null,
    title: String,
    content: String,
    val authorId: Long,
    val projectType: ProjectType,
    val interestingField: List<Field>,
    val wantedPosition: List<Position>,
    var isDeleted: Boolean = false,
    var isClosed: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    private var detail: PostDetail

    init {
        detail = PostDetail(title, content)
    }

    val title
        get() = detail.title

    val content
        get() = detail.content

    val canApply
        get() = !isClosed

    fun getSimplifiedTitle(length: Int = TITLE_SIMPLIFY_LENGTH): String {
        if (title.length > length) {
            return title.take(length) + "..."
        }

        return title
    }

    fun updateDetail(member: Member, title: String, content: String): Boolean {
        if (!isAuthor(member)) {
            throw MemberNotAuthorizedException("작성자만 수정할 수 있습니다.")
        }

        val newDetail = PostDetail(title, content)

        if (detail == newDetail) {
            return false
        }

        detail = newDetail
        return true
    }

    fun close() {
        check(!isClosed) { "이미 종료된 게시글입니다." }
        isClosed = true
    }

    fun unClose() {
        isClosed = false
    }

    private fun isAuthor(member: Member) = member.id == authorId

    fun delete() {
        check(!isDeleted) { "이미 삭제된 게시글입니다." }
        isDeleted = true
    }

    companion object {
        private const val TITLE_SIMPLIFY_LENGTH = 10
    }
}