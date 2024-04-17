package kwduo.member

import kwduo.annotation.NeedLogin
import kwduo.member.dto.MemberInfoResponseDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {
    @NeedLogin
    @GetMapping("/members/info")
    fun getMemberInfo(): MemberInfoResponseDTO {
        return MemberInfoResponseDTO(
            id = 1,
            nickname = "김개발",
            profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
            profileImgId = 3,
            department = "SOFTWARE",
            baekjoonId = "koosaga",
            position = "FRONTEND",
            techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
            bio = "안녕하세요. 개발의 왕 김개발입니다. 잘 부탁드립니다.",
            githubUrl = "https://github.com/google",
        )
    }
}
