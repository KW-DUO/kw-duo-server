package kwduo.post

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kwduo.bookmark.QBookmarkEntity
import kwduo.post.dto.PostSearchRequest
import kwduo.util.Page
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostSearchQueryDSLRepository(
    private val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(PostEntity::class.java) {
    fun searchFindTeammatePost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long> {
        val content =
            queryFactory.selectDistinct(post.id)
                .from(findTeammatePost)
                .join(post).on(findTeammatePost.id.eq(post.id))
                .leftJoin(positionEntity).on(findTeammatePost.id.eq(positionEntity.postId))
                .leftJoin(fieldEntity).on(findTeammatePost.id.eq(fieldEntity.postId))
                .leftJoin(bookmark).on(
                    bookmark.postId.eq(findTeammatePost.id),
                    when (requestMemberId) {
                        null -> bookmark.postId.isNull
                        else -> bookmark.memberId.eq(requestMemberId)
                    },
                    bookmark.isBookMarked.isTrue,
                )
                .where(
                    getPostSearchFilter(request),
                    findTeammatePost.isClosed.isFalse,
                    findTeammatePost.isDeleted.isFalse,
                )
                .orderBy(findTeammatePost.createdAt.desc())
                .limit(request.size.toLong())
                .offset(request.page * request.size.toLong())
                .fetch()

        val totalCount =
            queryFactory.select(findTeammatePost.countDistinct())
                .from(findTeammatePost)
                .join(post).on(findTeammatePost.id.eq(post.id))
                .leftJoin(positionEntity).on(findTeammatePost.id.eq(positionEntity.postId))
                .leftJoin(fieldEntity).on(findTeammatePost.id.eq(fieldEntity.postId))
                .leftJoin(bookmark).on(
                    bookmark.postId.eq(findTeammatePost.id),
                    when (requestMemberId) {
                        null -> bookmark.postId.isNull
                        else -> bookmark.memberId.eq(requestMemberId)
                    },
                    bookmark.isBookMarked.isTrue,
                )
                .where(
                    getPostSearchFilter(request),
                    findTeammatePost.isClosed.isFalse,
                    findTeammatePost.isDeleted.isFalse,
                )
                .fetchOne()!!

        return Page.of(
            content,
            totalCount.toInt(),
            request.size,
            request.page,
        )
    }

    fun searchFindTeamPost(
        requestMemberId: Long?,
        request: PostSearchRequest,
    ): Page<Long> {
        val content =
            queryFactory.selectDistinct(findTeamPost.id)
                .from(findTeamPost)
                .join(post).on(findTeamPost.id.eq(post.id))
                .leftJoin(positionEntity).on(findTeamPost.id.eq(positionEntity.postId))
                .leftJoin(fieldEntity).on(findTeamPost.id.eq(fieldEntity.postId))
                .leftJoin(bookmark).on(
                    bookmark.postId.eq(findTeamPost.id),
                    when (requestMemberId) {
                        null -> bookmark.postId.isNull
                        else -> bookmark.memberId.eq(requestMemberId)
                    },
                    bookmark.isBookMarked.isTrue,
                )
                .where(
                    getPostSearchFilter(request),
                    findTeamPost.isClosed.isFalse,
                    findTeamPost.isDeleted.isFalse,
                )
                .orderBy(findTeamPost.createdAt.desc())
                .limit(request.size.toLong())
                .offset(request.page * request.size.toLong())
                .fetch()

        val totalCount =
            queryFactory.select(findTeamPost.countDistinct())
                .from(findTeamPost)
                .join(post).on(findTeamPost.id.eq(post.id))
                .leftJoin(positionEntity).on(findTeamPost.id.eq(positionEntity.postId))
                .leftJoin(fieldEntity).on(findTeamPost.id.eq(fieldEntity.postId))
                .leftJoin(bookmark).on(
                    bookmark.postId.eq(findTeamPost.id),
                    when (requestMemberId) {
                        null -> bookmark.postId.isNull
                        else -> bookmark.memberId.eq(requestMemberId)
                    },
                    bookmark.isBookMarked.isTrue,
                )
                .where(
                    getPostSearchFilter(request),
                    findTeamPost.isClosed.isFalse,
                    findTeamPost.isDeleted.isFalse,
                )
                .fetchOne()!!

        return Page.of(
            content,
            totalCount.toInt(),
            request.size,
            request.page,
        )
    }

    private fun getPostSearchFilter(request: PostSearchRequest): BooleanExpression {
        var expression = post.isNotNull

        request.q?.let {
            expression = expression.and(post.title.contains(it).or(post.content.contains(it)))
        }

        request.getProjectType()?.let {
            expression = expression.and(post.projectType.eq(it))
        }

        request.getDepartment()?.let {
            expression = expression.and(post.department.eq(it))
        }

        request.className?.let {
            expression = expression.and(post.className.eq(it))
        }

        request.getPosition()?.let {
            expression = expression.and(positionEntity.position.eq(it))
        }

        request.getWantedField()?.let {
            expression = expression.and(fieldEntity.field.eq(it))
        }

        if (request.bookmarkOnly) {
            expression = expression.and(bookmark.id.isNotNull)
        }

        return expression
    }

    companion object {
        private val post = QPostEntity.postEntity
        private val findTeamPost = QFindTeamPostEntity.findTeamPostEntity
        private val findTeammatePost = QFindTeammatePostEntity.findTeammatePostEntity
        private val bookmark = QBookmarkEntity.bookmarkEntity
        private val positionEntity = QPostWantedPositionEntity.postWantedPositionEntity
        private val fieldEntity = QPostInterestingFieldEntity.postInterestingFieldEntity
    }
}
