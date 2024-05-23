package kwduo.member

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal class BaekjoonTierTest : BehaviorSpec({
    Given("code로부터") {
        When("BaekjoonTier enum을 생성할 수 있다") {
            withData(
                1 to BaekjoonTier.Bronze5,
                2 to BaekjoonTier.Bronze4,
                10 to BaekjoonTier.Silver1,
                30 to BaekjoonTier.Ruby1,
                31 to BaekjoonTier.Master,
            ) { (code, expectedTier) ->
                val tier = BaekjoonTier.fromCode(code)

                tier shouldBe expectedTier
            }
        }
    }
})
