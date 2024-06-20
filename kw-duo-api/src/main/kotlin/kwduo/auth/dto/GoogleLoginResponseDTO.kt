package kwduo.auth.dto

data class GoogleLoginResponseDTO(
    val oAuthId: String,
    val isSignup: Boolean,
    val accessToken: String?,
)
