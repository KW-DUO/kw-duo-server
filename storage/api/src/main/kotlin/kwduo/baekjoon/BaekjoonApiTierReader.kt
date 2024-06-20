package kwduo.baekjoon

import com.fasterxml.jackson.databind.JsonNode
import kwduo.member.BaekjoonTier
import kwduo.member.BaekjoonTierReader
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

private const val NOT_FOUND_ERROR_MESSAGE = "해당 아이디의 백준 정보가 없습니다."

@Component
class BaekjoonApiTierReader : BaekjoonTierReader {
    private val restClient = RestClient.create("https://solved.ac/api")

    override fun getTier(baekjoonId: String): BaekjoonTier {
        val response =
            restClient.get()
                .uri("/v3/user/show?handle=$baekjoonId")
                .retrieve()
                .onStatus({ it.is4xxClientError || it.is5xxServerError }) { _, _ ->
                    throw IllegalArgumentException(NOT_FOUND_ERROR_MESSAGE)
                }
                .body(JsonNode::class.java)

        requireNotNull(response) { NOT_FOUND_ERROR_MESSAGE }

        return BaekjoonTier.fromCode(response["tier"].asText().toInt())
    }
}
