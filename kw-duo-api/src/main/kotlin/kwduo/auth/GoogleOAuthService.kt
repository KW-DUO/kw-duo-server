package kwduo.auth

import com.fasterxml.jackson.databind.JsonNode
import kwduo.LinkedMultiValueMapMaker
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class GoogleOAuthService {
    private val restClient = RestClient.create()
    private val clientId = "1234"
    private val clientSecret = "1234"

    fun getAccessToken(code: String): String {
        try {
            val response =
                restClient.post()
                    .uri(TOKEN_URI)
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .body(
                        LinkedMultiValueMapMaker.make(
                            "code" to code,
                            "client_id" to clientId,
                            "client_secret" to clientSecret,
                            "redirect_uri" to REDIRECT_URI,
                            "grant_type" to "authorization_code",
                        ),
                    )
                    .retrieve()
                    .body(JsonNode::class.java)!!

            return response["access_token"].asText()!!
        } catch (e: Exception) {
            throw RuntimeException("Failed to get access token from google")
        }
    }

    fun getUserResource(accessToken: String): JsonNode {
        try {
            return restClient.get()
                .uri(USER_RESOURCE_URI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .retrieve()
                .body(JsonNode::class.java)!!
        } catch (e: Exception) {
            throw RuntimeException("Failed to get user resource from google")
        }
    }

    companion object {
        private const val REDIRECT_URI = "http://localhost:8080/auth/google"
        private const val TOKEN_URI = "https://oauth2.googleapis.com/token"
        private const val USER_RESOURCE_URI = "https://www.googleapis.com/oauth2/v2/userinfo"
    }
}
