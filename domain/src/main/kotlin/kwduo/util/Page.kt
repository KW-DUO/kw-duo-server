package kwduo.util

class Page<T>(
    val content: List<T>,
    val totalCount: Int,
    val totalPages: Int,
    val currentPage: Int,
) {
    companion object {
        fun <T> of(
            content: List<T>,
            totalCount: Int,
            pageSize: Int,
            currentPage: Int,
        ) = Page(
            content = content,
            totalCount = totalCount,
            totalPages = (totalCount + pageSize - 1) / pageSize,
            currentPage = currentPage,
        )
    }
}
