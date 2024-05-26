package kwduo.config

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.ServletException
import jakarta.validation.ConstraintViolationException
import kwduo.member.exception.MemberNotAuthorizedException
import kwduo.member.exception.MemberNotFoundException
import kwduo.post.exception.PostNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MaxUploadSizeExceededException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = KotlinLogging.logger {}

    @ExceptionHandler(MemberNotAuthorizedException::class)
    fun handleUserNotAuthorizedException(exception: MemberNotAuthorizedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(exception.message))
    }

    @ExceptionHandler(
        MemberNotFoundException::class,
        PostNotFoundException::class,
    )
    fun handleProfileNotFoundException(exception: Exception): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(exception.message))
    }

    @ExceptionHandler(
        IllegalStateException::class,
        IllegalArgumentException::class,
    )
    fun handleIllegalArgumentException(exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(exception.message))
    }

    @ExceptionHandler(
        HttpMessageConversionException::class,
        MethodArgumentTypeMismatchException::class,
        ServletException::class,
    )
    fun handleBadRequestException(exception: Exception): ResponseEntity<ErrorResponse> {
        log.error { exception.message }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("잘못된 요청입니다"))
    }

    @ExceptionHandler(BindException::class)
    fun handleMethodArgumentNotValidException(exception: BindException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(exception.fieldError?.defaultMessage))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(exception: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(exception.constraintViolations.first().message))
    }

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxUploadSizeExceededException(exception: MaxUploadSizeExceededException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("파일 크기가 너무 큽니다. (최대: 50MB)"))
    }

    @ExceptionHandler(Exception::class)
    fun handleNotHandledException(exception: Exception): ResponseEntity<ErrorResponse> {
//        Sentry.captureException(exception)

        val errorMessage: String = exception.stackTrace.joinToString(separator = "   ")
        log.error { "예상치 못한 예외가 발생했습니다." }
        log.error { exception.message }
        log.error { errorMessage }

        return ResponseEntity.internalServerError()
            .body(ErrorResponse("알 수 없는 문제가 발생했습니다."))
    }
}
