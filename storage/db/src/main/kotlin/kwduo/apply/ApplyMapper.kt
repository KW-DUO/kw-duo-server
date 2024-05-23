package kwduo.apply

object ApplyMapper {
    fun toEntity(apply: Apply) =
        ApplyEntity(
            id = apply.id,
            memberId = apply.memberId,
            postId = apply.postId,
        )

    fun toDomain(applyEntity: ApplyEntity) =
        Apply(
            id = applyEntity.id,
            memberId = applyEntity.memberId,
            postId = applyEntity.postId,
        )
}
