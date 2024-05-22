package kwduo.image

import java.time.LocalDateTime

class Image(
    val id: Long? = null,
    val url: String,
    var isUsed: Boolean,
    val uploadedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun use() {
        isUsed = true
    }

    fun unUse() {
        isUsed = false
    }
}
