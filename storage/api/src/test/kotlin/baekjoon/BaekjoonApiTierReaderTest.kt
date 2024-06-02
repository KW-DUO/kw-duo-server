package baekjoon

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kwduo.baekjoon.BaekjoonApiTierReader
import kwduo.member.BaekjoonTier

class BaekjoonApiTierReaderTest : FunSpec({
    xtest("solved 티어를 받아올 수 있다") {
        val tierReader = BaekjoonApiTierReader()

        val tier = tierReader.getTier("myc228")

        tier shouldBe BaekjoonTier.Platinum5
    }

    xtest("없는 닉네임이면 예외를 발생한다") {
        val tierReader = BaekjoonApiTierReader()

        shouldThrow<IllegalArgumentException> {
            tierReader.getTier("octoping")
        }
    }
})
