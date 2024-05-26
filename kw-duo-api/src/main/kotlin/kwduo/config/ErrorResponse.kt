package kwduo.config

import java.time.LocalDateTime

data class ErrorResponse(
    val message: String?,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)
