package kwduo.demo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KwduoServerApplicationTests {
    @Test
    fun contextLoads() {
        1 shouldBe 1
    }
}
