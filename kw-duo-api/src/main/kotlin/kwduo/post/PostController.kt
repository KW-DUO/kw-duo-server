package kwduo.post

import kwduo.member.schema.AuthorSchema
import kwduo.post.dto.PostSummaryResponseDTO
import kwduo.post.schema.BookmarkSchema
import kwduo.post.schema.PostDetailSchema
import kwduo.post.schema.PostSummarySchema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class PostController {
    @GetMapping("/find-teammate-posts")
    fun getFindTeammatePosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) `class`: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "false") notClosedOnly: Boolean,
    ): PostSummaryResponseDTO {
        if (q == "i dont want see") {
            return PostSummaryResponseDTO(posts = emptyList())
        }

        return PostSummaryResponseDTO(
            posts = listOf(
                PostSummarySchema(
                    id = 1,
                    postType = "FIND_TEAMMATE",
                    projectType = "CLASS_PROJECT",
                    title = "프론트엔드 개발자 구합니다",
                    author = AuthorSchema(
                        id = 1,
                        nickname = "김개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("FRONTEND", "BACKEND"),
                    department = "SOFTWARE",
                    techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                    `class` = "기계학습",
                    createdAt = LocalDateTime.now(),
                ),
                PostSummarySchema(
                    id = 2,
                    postType = "FIND_TEAMMATE",
                    projectType = "SIDE_PROJECT",
                    title = "백엔드 개발자 구합니다",
                    author = AuthorSchema(
                        id = 2,
                        nickname = "박개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("BACKEND"),
                    department = "SOFTWARE",
                    techStack = listOf("JAVA", "SPRING", "REACT"),
                    `class` = "기계학습",
                    createdAt = LocalDateTime.now(),
                ),
                PostSummarySchema(
                    id = 3,
                    postType = "FIND_TEAM",
                    projectType = "CLASS_PROJECT",
                    title = "개발 경험 만들어줄 팀을 구합니다",
                    author = AuthorSchema(
                        id = 3,
                        nickname = "스파이더맨",
                        profileImgUrl = null,
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("FRONTEND", "BACKEND"),
                    department = "INFORMATION_CONVERGENCE",
                    techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                    `class` = null,
                    createdAt = LocalDateTime.now(),
                )
            )
        )
    }

    @GetMapping("/find-team-posts")
    fun getFindTeamPosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) `class`: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "false") notClosedOnly: Boolean
    ): PostSummaryResponseDTO {
        return PostSummaryResponseDTO(
            posts = listOf(
                PostSummarySchema(
                    id = 1,
                    postType = "FIND_TEAMMATE",
                    projectType = "CLASS_PROJECT",
                    title = "프론트엔드 개발자 구합니다",
                    author = AuthorSchema(
                        id = 1,
                        nickname = "김개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("FRONTEND", "BACKEND"),
                    department = "SOFTWARE",
                    techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                    `class` = "기계학습",
                    createdAt = LocalDateTime.now(),
                ),
                PostSummarySchema(
                    id = 2,
                    postType = "FIND_TEAMMATE",
                    projectType = "SIDE_PROJECT",
                    title = "백엔드 개발자 구합니다",
                    author = AuthorSchema(
                        id = 2,
                        nickname = "박개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("BACKEND"),
                    department = "SOFTWARE",
                    techStack = listOf("JAVA", "SPRING", "REACT"),
                    `class` = "기계학습",
                    createdAt = LocalDateTime.now(),
                ),
                PostSummarySchema(
                    id = 3,
                    postType = "FIND_TEAM",
                    projectType = "CLASS_PROJECT",
                    title = "개발 경험 만들어줄 팀을 구합니다",
                    author = AuthorSchema(
                        id = 3,
                        nickname = "스파이더맨",
                        profileImgUrl = null,
                    ),
                    bookmark = BookmarkSchema(false),
                    wantedPosition = listOf("FRONTEND", "BACKEND"),
                    department = "INFORMATION_CONVERGENCE",
                    techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                    `class` = null,
                    createdAt = LocalDateTime.now(),
                )
            )
        )
    }

    @GetMapping("/posts/{id}")
    fun getPostDetail(
        @PathVariable id: Long,
    ): PostDetailSchema {
        return PostDetailSchema(
            id = id,
            postType = "FIND_TEAMMATE",
            projectType = "CLASS_PROJECT",
            title = "프론트엔드 개발자 구합니다",
            author = AuthorSchema(
                id = 1,
                nickname = "김개발",
                profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
            ),
            bookmark = BookmarkSchema(false),
            wantedPosition = listOf("FRONTEND", "BACKEND"),
            department = "SOFTWARE",
            techStack = listOf("KOTLIN", "JAVA", "SPRING"),
            `class` = "기계학습",
            recruitNumber = 3,
            interestingField = listOf("AI", "WEB"),
            createdAt = LocalDateTime.now(),
        )
    }
}
