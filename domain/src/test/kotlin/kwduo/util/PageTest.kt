package kwduo.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PageTest : BehaviorSpec({
    Given("of로") {
        When("Page를 생성하면") {
            arrayOf(
                Triple(10, 3, 4),
                Triple(3, 3, 1),
                Triple(1, 3, 1),
            )
                .forEach { (totalCount, pageSize, expectedTotalPage) ->
                    val page =
                        Page.of(
                            content = listOf(1, 2, 3),
                            totalCount = totalCount,
                            pageSize = pageSize,
                            currentPage = 1,
                        )

                    Then("${totalCount}개를 ${pageSize}의 크기로 페이지를 나누면 총 페이지 수는 ${expectedTotalPage}이다") {
                        page.totalPages shouldBe expectedTotalPage
                    }
                }
        }
    }
})
