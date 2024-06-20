package kwduo.member

enum class BaekjoonTier(
    private val code: Int,
    val value: String,
) {
    Bronze5(1, "BRONZE5"),
    Bronze4(2, "BRONZE4"),
    Bronze3(3, "BRONZE3"),
    Bronze2(4, "BRONZE2"),
    Bronze1(5, "BRONZE1"),
    Silver5(6, "SILVER5"),
    Silver4(7, "SILVER4"),
    Silver3(8, "SILVER3"),
    Silver2(9, "SILVER2"),
    Silver1(10, "SILVE1R"),
    Gold5(11, "GOLD5"),
    Gold4(12, "GOLD4"),
    Gold3(13, "GOLD3"),
    Gold2(14, "GOLD2"),
    Gold1(15, "GOLD1"),
    Platinum5(16, "PLATINUM5"),
    Platinum4(17, "PLATINUM4"),
    Platinum3(18, "PLATINUM3"),
    Platinum2(19, "PLATINUM2"),
    Platinum1(20, "PLATINUM1"),
    Diamond5(21, "DIAMOND5"),
    Diamond4(22, "DIAMOND4"),
    Diamond3(23, "DIAMOND3"),
    Diamond2(24, "DIAMOND2"),
    Diamond1(25, "DIAMOND1"),
    Ruby5(26, "RUBY5"),
    Ruby4(27, "RUBY4"),
    Ruby3(28, "RUBY3"),
    Ruby2(29, "RUBY2"),
    Ruby1(30, "RUBY1"),
    Master(31, "MASTER"),
    ;

    companion object {
        fun fromCode(code: Int) = entries.first { it.code == code }
    }
}
