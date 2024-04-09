package bookmark

class BookMark(
    val id: Long? = null,
    val userId: Long,
    val postId: Long,
    var isBookMarked: Boolean,
) {
}