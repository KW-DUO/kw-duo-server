package kwduo.image

import org.springframework.data.jpa.repository.JpaRepository

interface ImageJpaRepository : JpaRepository<ImageEntity, Long>
