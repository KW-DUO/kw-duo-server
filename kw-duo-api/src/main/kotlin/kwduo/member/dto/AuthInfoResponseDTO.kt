package kwduo.member.dto

data class AuthInfoResponseDTO(
    val isLoggedIn: Boolean,
    val memberId: Long?,
)
