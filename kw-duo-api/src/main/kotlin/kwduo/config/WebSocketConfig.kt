package kwduo.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@EnableWebSocketMessageBroker
@Configuration
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/chat") // 발행자가 "/topic"의 경로로 메시지를 주면 구독자들에게 전달
        config.setApplicationDestinationPrefixes("/app") // 발행자가 "/app"의 경로로 메시지를 주면 가공을 해서 구독자들에게 전달
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chatting")
            .setAllowedOrigins(
                "http://localhost:3000",
                "https://kw-duo.vercel.app",
            )
            .withSockJS() // 커넥션을 맺는 경로 설정. 만약 WebSocket을 사용할 수 없는 브라우저라면 다른 방식을 사용하도록 설정
    }
}
