package kwduo.image

object ImageMapper {
    fun toDomain(imageEntity: ImageEntity) =
        Image(
            id = imageEntity.id,
            url = imageEntity.url,
            isUsed = imageEntity.isUsed,
            uploadedAt = imageEntity.uploadedAt,
        )

    fun toEntity(image: Image) =
        ImageEntity(
            id = image.id,
            url = image.url,
            isUsed = image.isUsed,
            uploadedAt = image.uploadedAt,
        )
}
