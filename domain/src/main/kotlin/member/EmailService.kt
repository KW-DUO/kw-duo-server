package member

import infra.EmailSender
import token.EmailTokenService

class EmailService(
    private val emailSender: EmailSender,
    private val emailTokenService: EmailTokenService,
) {
    fun sendAuthorizeEmail(member: Member) {
        check(!member.isEmailAuthenticated) { "이미 이메일 인증된 유저입니다" }

        val token = emailTokenService.createEmailToken(member.id!!)

        emailSender.sendEmail(
            email = member.email,
            title = "[DUO] 가입 인증 메일",
            content = "이메일 토큰: ${token.token}", // TODO: html 같은 걸로 이쁘게 만들긴 해야할듯 함
        )
    }
}