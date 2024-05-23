package kwduo.image

import org.springframework.stereotype.Component

@Component
class ImageReader(
    private val imageRepository: ImageRepository,
) {
    fun findById(imageId: Long): Image {
        return imageRepository.findById(imageId)
            ?: throw IllegalArgumentException("해당 이미지를 찾을 수 없습니다.")
    }
}
