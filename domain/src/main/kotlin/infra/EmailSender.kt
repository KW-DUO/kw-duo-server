package infra

interface EmailSender {
    fun sendEmail(
        email: String,
        title: String,
        content: String,
    )
}
