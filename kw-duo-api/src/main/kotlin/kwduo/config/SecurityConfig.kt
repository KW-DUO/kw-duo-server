package kwduo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { obj -> obj.disable() }
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers(
                    "/h2-console/**",
                    "/favicon.ico",
                    "/swagger-ui/**",
                    "/swagger-resources/**",
                    "/v3/api-docs/**",
                    "/api-docs",
                ).permitAll()
            }
            .exceptionHandling { exception ->
//                exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                exception.accessDeniedHandler(jwtAccessDeniedHandler)
            }
            .headers { headers -> headers.frameOptions { it.sameOrigin() } }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                request.requestMatchers("/**").permitAll()
                request.anyRequest().authenticated() // 나머지 API 는 전부 인증 필요
            } // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
//            .apply(JwtSecurityConfig(tokenProvider, authUseCase, tokenConstants))

        return http.build()
    }
}
