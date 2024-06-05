package kwduo.post

import kwduo.member.schema.AuthorSchema
import kwduo.post.schema.BookmarkSchema
import kwduo.post.schema.PostDetailSchema
import java.time.LocalDateTime

object DummyPosts {
    val detail = { id: Long ->
        PostDetailSchema(
            id = id,
            postType = "FIND_TEAMMATE",
            projectType = "CLASS_PROJECT",
            title = "프론트엔드 개발자 구합니다",
            content =
                "프론트엔드 개발자를 구합니다. Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
            author =
                AuthorSchema(
                    id = 1,
                    nickname = "김개발",
                ),
            bookmark = BookmarkSchema(false),
            wantedPosition = listOf("FRONTEND", "BACKEND"),
            department = "SOFTWARE",
            techStack = listOf("KOTLIN", "JAVA", "SPRING"),
            className = "기계학습",
            recruitNumber = 3,
            interestingField = listOf("AI", "WEB"),
            createdAt = LocalDateTime.now(),
        )
    }
}
