package kwduo.apply

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.auth.LoggedInMemberReader
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Apply")
@RestController
class ApplyController(
    private val applyService: ApplyService,
) {
    @NeedLogin
    @Operation(summary = "지원하기")
    @PostMapping("/apply/{postId}")
    fun apply(
        @PathVariable postId: Long,
    ) {
        applyService.apply(
            memberId = LoggedInMemberReader.currentMemberId,
            postId = postId,
        )
    }
}
