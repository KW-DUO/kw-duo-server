package kwduo.member

data class KwEmailInfo(
    val email: String,
    private var isAuthenticated: Boolean = false,
) {
    init {
        require(email.isNotBlank()) { "이메일은 필수입니다." }
        require(isKwEmailDomain()) { "이메일은 광운대학교 이메일이어야 합니다." }
        require(isIdValid()) { "이메일 형식이 올바르지 않습니다." }
    }

    fun authenticate() {
        check(!isAuthenticated) { "이미 인증된 이메일입니다." }
        isAuthenticated = true
    }

    fun isAuthenticated() = isAuthenticated

    private fun isKwEmailDomain() = email.endsWith(KW_EMAIL_DOMAIN_POSTFIX)

    private fun isIdValid(): Boolean {
        val splittedEmail = email.split(KW_EMAIL_DOMAIN_POSTFIX)

        if (splittedEmail.size != 2) {
            return false
        }

        val id = splittedEmail[0]

        return id.isNotBlank() && id.matches(Regex(VALID_ID_REGEX))
    }

    companion object {
        private const val KW_EMAIL_DOMAIN_POSTFIX = "@kw.ac.kr"
        private const val VALID_ID_REGEX = "^[a-zA-Z0-9._%+-]+"
    }
}
