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
    PLANNER("기획", "PLANNER"),
    DESIGN("디자인", "DESIGN"),
    MACHINE_LEARNING("머신러닝", "MACHINE_LEARNING"),
    BLOCKCHAIN("블록체인", "BLOCKCHAIN"),
    EMBEDDED("임베디드", "EMBEDDED"),
    OTHER("기타", "OTHER"),
    ANY("전체", "ANY"),
    ;

    companion object {
        fun of(position: String) = entries.first { it.value == position }
    }
}
