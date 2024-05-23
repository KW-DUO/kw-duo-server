package kwduo.member

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal class KwEmailInfoTest : BehaviorSpec({
    Given("이메일이 광운대학교 도메인이고") {
        And("이메일 형식이 올바르면") {
            val kwEmail = "duo@kw.ac.kr"

            When("KwEmailInfo를 생성하면") {
                val info = KwEmailInfo(kwEmail)

                Then("KwEmailInfo가 생성된다.") {
                }

                Then("최초 생성 시 인증은 되어있지 않다.") {
                    info.isAuthenticated() shouldBe false
                }

                And("인증할 경우") {
                    info.authenticate()

                    Then("인증 여부가 true가 된다") {
                        info.isAuthenticated() shouldBe true
                    }

                    And("이미 인증된 경우 에러를 발생한다") {
                        val result =
                            shouldThrow<IllegalStateException> {
                                info.authenticate()
                            }

                        result.message shouldBe "이미 인증된 이메일입니다."
                    }
                }
            }
        }

        And("이메일 형식이 올바르지 않을 경우") {
            When("KwEmailInfo를 생성하면") {
                withData(
                    nameFn = { "예외가 발생한다 ($it)" },
                    "@kw.ac.kr",
                    " @kw.ac.kr",
                    "@@kw.ac.kr",
                    "%^%^@kw.ac.kr",
                    "a$%b123@kw.ac.kr",
                    "duo@kw.ac.kr@kw.ac.kr",
                ) { invalidEmail ->
                    val exception =
                        shouldThrow<IllegalArgumentException> {
                            KwEmailInfo(invalidEmail)
                        }

                    exception.message shouldBe "이메일 형식이 올바르지 않습니다."
                }
            }
        }
    }

    Given("이메일이 광운대학교 도메인이 아닐 때") {
        When("KwEmailInfo를 생성하면") {
            withData(
                nameFn = { "예외가 발생한다 ($it)" },
                "duo@naver.com",
                "duo@google.com",
                "duo@daum.net",
                "duo@kw.com",
                "duo@kw.ac.en",
                "duo@kw.ac.krr",
            ) { invalidEmail ->
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        KwEmailInfo(invalidEmail)
                    }

                exception.message shouldBe "이메일은 광운대학교 이메일이어야 합니다."
            }
        }
    }
})
