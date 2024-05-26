package kwduo.post

import kwduo.member.Department
import kwduo.member.Position
import kwduo.member.TechStack
import kwduo.member.exception.MemberNotAuthorizedException
import java.time.LocalDateTime

abstract class Post(
    val id: Long? = null,
    title: String,
    content: String,
    val authorId: Long,
    var projectType: ProjectType,
    var className: String?,
    var department: Department,
    var interestingField: List<Field>,
    var wantedPosition: List<Position>,
    var techStack: List<TechStack>,
    var isDeleted: Boolean = false,
    var isClosed: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    protected var detail: PostDetail

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

    fun close(memberId: Long) {
        if (!isAuthor(memberId)) {
            throw MemberNotAuthorizedException("작성자만 모집을 마감할 수 있습니다.")
        }

        check(!isClosed) { "이미 종료된 게시글입니다." }
        isClosed = true
    }

    fun unClose(memberId: Long) {
        if (!isAuthor(memberId)) {
            throw MemberNotAuthorizedException("작성자만 모집을 다시 시작할 수 있습니다.")
        }
        check(isClosed) { "이미 모집 중인 게시글입니다." }

        isClosed = false
    }

    protected fun isAuthor(memberId: Long) = memberId == authorId

    fun delete(memberId: Long) {
        if (!isAuthor(memberId)) {
            throw MemberNotAuthorizedException("작성자만 삭제할 수 있습니다.")
        }

        check(!isDeleted) { "이미 삭제된 게시글입니다." }
        isDeleted = true
    }

    companion object {
        private const val TITLE_SIMPLIFY_LENGTH = 10
    }
}
