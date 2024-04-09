package image

import java.time.LocalDateTime

class Image(
    val id: Long? = null,
    val url: String,
    private var isUsed: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    fun use() {
        isUsed = true
    }

    fun unUse() {
        isUsed = false
    }
}
