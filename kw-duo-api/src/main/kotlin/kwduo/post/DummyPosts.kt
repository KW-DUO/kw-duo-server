package kwduo.post

import kwduo.member.schema.AuthorSchema
import kwduo.post.schema.BookmarkSchema
import kwduo.post.schema.PostDetailSchema
import kwduo.post.schema.PostSummarySchema
import java.time.LocalDateTime

object DummyPosts {
    val postSummary =
        listOf(
            PostSummarySchema(
                id = 1,
                postType = "FIND_TEAMMATE",
                projectType = "CLASS_PROJECT",
                title = "프론트엔드 개발자 구합니다",
                author =
                    AuthorSchema(
                        id = 1,
                        nickname = "김개발",
                    ),
                bookmark = BookmarkSchema(false),
                wantedPosition = listOf("FRONTEND", "BACKEND"),
                wantedField = listOf("WEB", "AI"),
                department = "SOFTWARE",
                techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                className = "기계학습",
                createdAt = LocalDateTime.now(),
            ),
            PostSummarySchema(
                id = 2,
                postType = "FIND_TEAMMATE",
                projectType = "SIDE_PROJECT",
                title = "백엔드 개발자 구합니다",
                author =
                    AuthorSchema(
                        id = 2,
                        nickname = "박개발",
                    ),
                bookmark = BookmarkSchema(false),
                wantedPosition = listOf("BACKEND"),
                wantedField = listOf("WEB"),
                department = "SOFTWARE",
                techStack = listOf("JAVA", "SPRING", "REACT"),
                className = "기계학습",
                createdAt = LocalDateTime.now(),
            ),
            PostSummarySchema(
                id = 3,
                postType = "FIND_TEAM",
                projectType = "CLASS_PROJECT",
                title = "개발 경험 만들어줄 팀을 구합니다",
                author =
                    AuthorSchema(
                        id = 3,
                        nickname = "스파이더맨",
                    ),
                bookmark = BookmarkSchema(false),
                wantedPosition = listOf("FRONTEND", "BACKEND"),
                wantedField = listOf("AI", "GAME"),
                department = "INFORMATION_CONVERGENCE",
                techStack = listOf("KOTLIN", "JAVA", "SPRING", "JPA"),
                className = null,
                createdAt = LocalDateTime.now(),
            ),
        )

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
