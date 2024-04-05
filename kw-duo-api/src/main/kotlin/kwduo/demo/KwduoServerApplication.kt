package kwduo.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KwduoServerApplication

fun main(args: Array<String>) {
    runApplication<KwduoServerApplication>(*args)
}
