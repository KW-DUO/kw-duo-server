package kwduo.util

import java.util.Random

object RandomSlugMaker {
    private const val KEYS = "abcdefghijklmnopqrstuvwxyz1234567890"
    private const val DEFAULT_SLUG_LENGTH = 8
    private val random = Random()

    fun makeSlug(slugLength: Int = DEFAULT_SLUG_LENGTH): String {
        val sb = StringBuilder()

        while (sb.length < slugLength) {
            sb.append(KEYS[random.nextInt(KEYS.length)])
        }

        return sb.toString()
    }
}
