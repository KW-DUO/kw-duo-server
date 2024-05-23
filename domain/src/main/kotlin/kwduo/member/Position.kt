package kwduo.member

enum class Position(
    val displayName: String,
    val value: String,
) {
    FRONTEND("프론트엔드", "FRONTEND"),
    BACKEND("백엔드", "BACKEND"),
    ANDROID("안드로이드", "ANDROID"),
    IOS("iOS", "IOS"),
    GAME("게임", "GAME"),
    ;

    companion object {
        fun of(position: String) = entries.first { it.value == position }
    }
}
