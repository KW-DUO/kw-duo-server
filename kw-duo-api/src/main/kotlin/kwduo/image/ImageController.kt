package kwduo.image

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kwduo.annotation.NeedLogin
import kwduo.image.dto.ImageUploadResponseDTO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "Image")
@RestController
class ImageController {
    @NeedLogin
    @Operation(summary = "프로필 이미지 업로드")
    @PostMapping("/file/upload-profile-image", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        @RequestPart(value = "kwduo/image") image: MultipartFile,
    ): ImageUploadResponseDTO {
        validateImage(image)

        return ImageUploadResponseDTO(1L, "https://avatars.githubusercontent.com/u/123415678?v=4")
    }

    private fun validateImage(image: MultipartFile) {
        require(image.contentType?.startsWith("kwduo/image") != false) { "이미지 파일이 아닙니다." }
    }
}
