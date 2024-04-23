package post

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith
import io.kotest.matchers.string.shouldNotEndWith
import io.kotest.matchers.string.shouldStartWith

class PostTest : BehaviorSpec({

    Given("제목이 10자가 넘는 글은") {
        val post =
            FindTeamPost(
                title = "it's_too_long_to_read",
                content = "content",
                authorId = 1L,
                projectType = ProjectType.SIDE_PROJECT,
                interestingField = emptyList(),
                wantedPosition = emptyList(),
            )

        When("simplifiedTitle을 얻어왔을 때") {
            val title = post.getSimplifiedTitle()

            Then("10자 까지만 불러와진 후 ...이 뒤에 붙는다") {
                title.length shouldBe 10 + 3
                title shouldStartWith post.title.take(10)
                title shouldEndWith "..."
            }
        }
    }

    Given("제목이 10글자 이하인 글은") {
        val post =
            FindTeamPost(
                title = "it's_short",
                content = "content",
                authorId = 1L,
                projectType = ProjectType.SIDE_PROJECT,
                interestingField = emptyList(),
                wantedPosition = emptyList(),
            )

        When("simplifiedTitle을 얻어왔을 때") {
            val title = post.getSimplifiedTitle()

            Then("제목이 그대로 반환된다") {
                title shouldBe post.title
                title shouldNotEndWith "..."
            }
        }
    }
})
