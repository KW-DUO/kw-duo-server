package kwduo.member

enum class Position(
    val displayName: String,
) {
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    ANDROID("안드로이드"),
    IOS("iOS"),
    GAME("게임"),
    ;

    companion object {
        fun of(position: String) = entries.first { it.displayName == position }
    }
}
