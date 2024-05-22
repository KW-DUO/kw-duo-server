package kwduo.image

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ImageRepositoryImpl(
    private val imageJpaRepository: ImageJpaRepository,
) : ImageRepository {
    @Transactional
    override fun save(image: Image): Image {
        val entity = imageJpaRepository.save(ImageMapper.toEntity(image))
        return ImageMapper.toDomain(entity)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Image? {
        return imageJpaRepository.findByIdOrNull(id)
            ?.let { ImageMapper.toDomain(it) }
    }
}
