package kwduo.post

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import kwduo.annotation.NeedLogin
import kwduo.member.schema.AuthorSchema
import kwduo.member.schema.MemberSummarySchema
import kwduo.post.dto.FindTeamPostWriteRequestDTO
import kwduo.post.dto.FindTeammatePostWriteRequestDTO
import kwduo.post.dto.PostApplicantResponseDTO
import kwduo.post.dto.PostSummaryResponseDTO
import kwduo.post.dto.PostWriteResponseDTO
import kwduo.post.schema.BookmarkSchema
import kwduo.post.schema.PostDetailSchema
import kwduo.post.schema.PostSummarySchema
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "Post")
@RestController
class PostController {
    @Operation(summary = "팀원 찾기 글 조회")
    @GetMapping("/posts/find-teammate")
    fun getFindTeammatePosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) `class`: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "false") notClosedOnly: Boolean,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0") page: Int,
        @Valid @Min(0) @Max(20) @RequestParam(required = false, defaultValue = "20") size: Int,
    ): PostSummaryResponseDTO {
        if (q == "i dont want see") {
            return PostSummaryResponseDTO(posts = emptyList())
        }

        return PostSummaryResponseDTO(
            posts =
                listOf(
                    PostSummarySchema(
                        id = 1,
                        postType = "FIND_TEAMMATE",
                        projectType = "CLASS_PROJECT",
                        title = "프론트엔드 개발자 구합니다",
                        author =
                            AuthorSchema(
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
                        author =
                            AuthorSchema(
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
                        author =
                            AuthorSchema(
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
                    ),
                ),
        )
    }

    @Operation(summary = "팀 찾기 글 조회")
    @GetMapping("/posts/find-team")
    fun getFindTeamPosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) `class`: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "false") notClosedOnly: Boolean,
        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0") page: Int,
        @Valid @Min(0) @Max(20) @RequestParam(required = false, defaultValue = "20") size: Int,
    ): PostSummaryResponseDTO {
        return PostSummaryResponseDTO(
            posts =
                listOf(
                    PostSummarySchema(
                        id = 1,
                        postType = "FIND_TEAMMATE",
                        projectType = "CLASS_PROJECT",
                        title = "프론트엔드 개발자 구합니다",
                        author =
                            AuthorSchema(
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
                        author =
                            AuthorSchema(
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
                        author =
                            AuthorSchema(
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
                    ),
                ),
        )
    }

    @Operation(summary = "글 상세 조회")
    @GetMapping("/posts/{postId}")
    fun getPostDetail(
        @PathVariable postId: Long,
    ): PostDetailSchema {
        return PostDetailSchema(
            id = postId,
            postType = "FIND_TEAMMATE",
            projectType = "CLASS_PROJECT",
            title = "프론트엔드 개발자 구합니다",
            content =
                "프론트엔드 개발자를 구합니다. Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
            author =
                AuthorSchema(
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

    @Operation(summary = "지원자 조회")
    @GetMapping("/posts/{postId}/applicant")
    fun getApplicant(
        @PathVariable postId: Long,
    ): PostApplicantResponseDTO {
        return PostApplicantResponseDTO(
            applicants =
                listOf(
                    MemberSummarySchema(
                        id = 1,
                        nickname = "김개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                        department = "SOFTWARE",
                        techStack = listOf("KOTLIN", "JAVA", "SPRING"),
                    ),
                    MemberSummarySchema(
                        id = 2,
                        nickname = "박개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                        department = "SOFTWARE",
                        techStack = listOf("REACT", "NEXTJS", "SWIFT"),
                    ),
                    MemberSummarySchema(
                        id = 3,
                        nickname = "이개발",
                        profileImgUrl = "https://avatars.githubusercontent.com/u/12345678?v=4",
                        department = "INFORMATION_CONVERGENCE",
                        techStack = emptyList(),
                    ),
                ),
        )
    }

    @NeedLogin
    @Operation(summary = "팀원 찾기 글 작성")
    @PostMapping("/posts/find-teammate")
    fun createFindTeammatePost(
        @RequestBody request: FindTeammatePostWriteRequestDTO,
    ): PostWriteResponseDTO {
        return PostWriteResponseDTO(
            postId = 1,
        )
    }

    @NeedLogin
    @Operation(summary = "팀원 찾기 글 수정")
    @PutMapping("/posts/find-teammate/{postId}")
    fun updateFindTeammatePost(
        @PathVariable postId: Long,
        @RequestBody request: FindTeammatePostWriteRequestDTO,
    ) {
        // 글 수정 로직
    }

    @NeedLogin
    @Operation(summary = "팀 찾기 글 작성")
    @PostMapping("/posts/find-team")
    fun createFindTeamPost(
        @RequestBody request: FindTeamPostWriteRequestDTO,
    ): PostWriteResponseDTO {
        return PostWriteResponseDTO(
            postId = 2,
        )
    }

    @NeedLogin
    @Operation(summary = "팀 찾기 글 수정")
    @PutMapping("/posts/find-team/{postId}")
    fun updateFindTeamPost(
        @PathVariable postId: Long,
        @RequestBody request: FindTeamPostWriteRequestDTO,
    ) {
        // 글 수정 로직
    }

    @NeedLogin
    @Operation(summary = "팀 찾기 글 모집 마감")
    @PostMapping("/posts/{postId}/close")
    fun closePost(
        @PathVariable postId: Long,
    ) {
        // 글 모집 마감 로직
    }

    @NeedLogin
    @Operation(summary = "글 삭제")
    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ) {
        // 글 삭제 로직
    }
}
