package kwduo.image

interface ImageRepository {
    fun save(image: Image): Image

    fun findById(id: Long): Image?
}
