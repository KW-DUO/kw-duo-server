package image

class ImageService(
    private val imageRepository: ImageRepository,
) {
    fun use(imageId: Long) {
        val image =
            imageRepository.findById(imageId)
                ?: throw IllegalArgumentException("이미지가 없습니다.")

        image.use()
    }
}
