package kwduo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan(basePackages = ["kwduo"])
@SpringBootApplication
class KwduoServerApplication

fun main(args: Array<String>) {
    runApplication<KwduoServerApplication>(*args)
}
