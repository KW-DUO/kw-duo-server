package kwduo.apply

import io.swagger.v3.oas.annotations.Operation
import kwduo.annotation.NeedLogin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplyController {
    @NeedLogin
    @Operation(summary = "지원하기")
    @PostMapping("/apply/{postId}")
    fun apply(
        @PathVariable postId: Long,
    ) {
    }
}
