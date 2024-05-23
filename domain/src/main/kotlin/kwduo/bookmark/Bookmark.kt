package kwduo.bookmark

import kwduo.member.exception.MemberNotAuthorizedException

class Bookmark(
    val id: Long? = null,
    val memberId: Long,
    val postId: Long,
    var isBookMarked: Boolean,
) {
    fun bookmark() {
        isBookMarked = true
    }

    fun delete(requestMemberId: Long) {
        if (memberId != requestMemberId) {
            throw MemberNotAuthorizedException()
        }

        isBookMarked = false
    }
}
