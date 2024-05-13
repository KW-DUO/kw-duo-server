package kwduo.member

enum class Department(
    val displayName: String,
) {
    COMPUTER_INFORMATION_ENGINEERING("컴퓨터정보공학"),
    SOFTWARE("소프트웨어"),
    INFORMATION_CONVERGENCE("정보융합"),
    ;

    companion object {
        fun of(displayName: String) = entries.first { it.displayName == displayName }
    }
}
