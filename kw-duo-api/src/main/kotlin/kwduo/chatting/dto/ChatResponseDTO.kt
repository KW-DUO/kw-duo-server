package kwduo.chatting.dto

import kwduo.chatting.schema.ChatSchema

data class ChatResponseDTO(
    val chat: List<ChatSchema>
)
