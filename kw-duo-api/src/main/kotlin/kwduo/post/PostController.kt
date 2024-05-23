package kwduo.post

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.auth.LoggedInMemberReader
import kwduo.member.schema.MemberSummarySchema
import kwduo.post.dto.FindTeamPostWriteRequest
import kwduo.post.dto.FindTeamPostWriteRequestDTO
import kwduo.post.dto.FindTeammatePostWriteRequest
import kwduo.post.dto.FindTeammatePostWriteRequestDTO
import kwduo.post.dto.PostApplicantResponseDTO
import kwduo.post.dto.PostSummaryResponseDTO
import kwduo.post.dto.PostWriteResponseDTO
import kwduo.post.schema.PostDetailSchema
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Post")
@RestController
class PostController(
    private val postService: PostService,
    private val postSearchService: PostSearchService,
) {
    @Operation(summary = "팀원 찾기 글 조회")
    @GetMapping("/posts/find-teammate")
    fun getFindTeammatePosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) className: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): PostSummaryResponseDTO {
        if (q == "i dont want see") {
            return PostSummaryResponseDTO(posts = emptyList(), 32, 3, 3)
        }

        return PostSummaryResponseDTO(DummyPosts.postSummary, 32, 3, 3)
    }

    @Operation(summary = "팀 찾기 글 조회")
    @GetMapping("/posts/find-team")
    fun getFindTeamPosts(
        @RequestParam(required = false) q: String?,
        @RequestParam(required = false) projectType: String?,
        @RequestParam(required = false) department: String?,
        @RequestParam(required = false) className: String?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) wantedField: String?,
        @RequestParam(required = false, defaultValue = "false") bookmarkOnly: Boolean,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): PostSummaryResponseDTO {
        return PostSummaryResponseDTO(DummyPosts.postSummary, 51, 1, 32)
    }

    @Operation(summary = "글 상세 조회")
    @GetMapping("/posts/{postId}")
    fun getPostDetail(
        @PathVariable postId: Long,
    ): PostDetailSchema {
        return DummyPosts.detail(postId)
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
        val post =
            postService.writeFindTeammatePost(
                FindTeammatePostWriteRequest(
                    title = request.title,
                    content = request.content,
                    authorId = LoggedInMemberReader.currentMemberId,
                    projectType = request.projectType,
                    interestingField = request.interestingField,
                    wantedPosition = request.wantedPosition,
                    techStack = request.techStack,
                    recruitNumber = request.recruitNumber,
                ),
            )

        return PostWriteResponseDTO(post.id!!)
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
        val post =
            postService.writeFindTeamPost(
                FindTeamPostWriteRequest(
                    title = request.title,
                    content = request.content,
                    authorId = LoggedInMemberReader.currentMemberId,
                    projectType = request.projectType,
                    interestingField = request.interestingField,
                    wantedPosition = request.wantedPosition,
                    techStack = request.techStack,
                ),
            )

        return PostWriteResponseDTO(post.id!!)
    }

    @NeedLogin
    @Operation(summary = "팀 찾기 글 수정")
    @PutMapping("/posts/find-team/{postId}")
    fun updateFindTeamPost(
        @PathVariable postId: Long,
        @RequestBody request: FindTeamPostWriteRequestDTO,
    ) {
        val requestMemberId = LoggedInMemberReader.currentMemberId

//        postService.updatePostDetail(
//            requestMemberId = requestMemberId,
//            postId = postId,
//            title = request.title,
//            content = request.content,
//        )
    }

    @NeedLogin
    @Operation(summary = "팀 찾기 글 모집 마감")
    @PostMapping("/posts/{postId}/close")
    fun closePost(
        @PathVariable postId: Long,
    ) {
        postService.closePost(
            requestMemberId = LoggedInMemberReader.currentMemberId,
            postId = postId,
        )
    }

    @NeedLogin
    @Operation(summary = "글 삭제")
    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ) {
        postService.deletePost(
            requestMemberId = LoggedInMemberReader.currentMemberId,
            postId = postId,
        )
    }
}
