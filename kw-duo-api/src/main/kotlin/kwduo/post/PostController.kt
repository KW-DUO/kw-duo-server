package kwduo.post

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.auth.LoggedInMemberReader
import kwduo.member.schema.MemberSummarySchema
import kwduo.post.dto.*
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
    private val postApplicantService: PostApplicantService,
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
        val posts =
            postSearchService.searchFindTeammatePost(
                LoggedInMemberReader.currentNullishMemberId,
                PostSearchRequest(
                    q = q,
                    projectType = projectType,
                    department = department,
                    className = className,
                    position = position,
                    wantedField = wantedField,
                    bookmarkOnly = bookmarkOnly,
                    page = page,
                    size = size,
                ),
            )

        return PostSummaryResponseDTO(posts)
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
        val posts =
            postSearchService.searchFindTeamPost(
                LoggedInMemberReader.currentNullishMemberId,
                PostSearchRequest(
                    q = q,
                    projectType = projectType,
                    department = department,
                    className = className,
                    position = position,
                    wantedField = wantedField,
                    bookmarkOnly = bookmarkOnly,
                    page = page,
                    size = size,
                ),
            )

        return PostSummaryResponseDTO(posts)
    }

    @Operation(summary = "글 상세 조회")
    @GetMapping("/posts/{postId}")
    fun getPostDetail(
        @PathVariable postId: Long,
    ): PostDetailSchema {
        if (postId == 999L) {
            return DummyPosts.detail(postId)
        }

        val postDetail =
            postService.getPostDetail(
                LoggedInMemberReader.currentNullishMemberId,
                postId,
            )

        return PostDetailSchema(postDetail)
    }

    @Operation(summary = "지원자 조회")
    @GetMapping("/posts/{postId}/applicant")
    fun getApplicant(
        @PathVariable postId: Long,
    ): PostApplicantResponseDTO {
        val applicants = postApplicantService.getApplicants(postId)

        return PostApplicantResponseDTO(
            applicants.map {
                MemberSummarySchema(
                    id = it.id,
                    nickname = it.nickname,
                    department = it.department.displayName,
                    techStack = it.techStack.map { it.value },
                )
            },
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
                    className = request.className,
                    department = request.department,
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
        val requestMemberId = LoggedInMemberReader.currentMemberId

        postService.updatePostDetail(
            requestMemberId = requestMemberId,
            postId = postId,
            request =
                FindTeammatePostEditRequest(
                    title = request.title,
                    content = request.content,
                    projectType = request.projectType,
                    className = request.className,
                    department = request.department,
                    interestingField = request.interestingField,
                    wantedPosition = request.wantedPosition,
                    techStack = request.techStack,
                    recruitNumber = request.recruitNumber,
                ),
        )
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
                    className = request.className,
                    department = request.department,
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

        postService.updatePostDetail(
            requestMemberId = requestMemberId,
            postId = postId,
            request =
                FindTeamPostEditRequest(
                    title = request.title,
                    content = request.content,
                    projectType = request.projectType,
                    className = request.className,
                    department = request.department,
                    interestingField = request.interestingField,
                    wantedPosition = request.wantedPosition,
                    techStack = request.techStack,
                ),
        )
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
