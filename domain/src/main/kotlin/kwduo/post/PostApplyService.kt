package kwduo.post

import kwduo.apply.ApplyService
import kwduo.chatting.ChattingService
import kwduo.member.Member
import kwduo.member.MemberReader
import kwduo.post.exception.PostNotFoundException
import org.springframework.stereotype.Service

@Service
class PostApplyService(
    private val applyService: ApplyService,
    private val chattingService: ChattingService,
    private val memberReader: MemberReader,
    private val postRepository: PostRepository,
) {
    fun applyToPost(
        requestMemberId: Long,
        postId: Long,
    ): Long {
        val post =
            postRepository.findById(postId)
                ?: throw PostNotFoundException()

        val member = memberReader.findById(requestMemberId)

        return when (post) {
            is FindTeammatePost -> applyTeam(post, member)
            is FindTeamPost -> inviteTeammate(post, member)
            else -> throw IllegalArgumentException("지원할 수 없는 게시물입니다.")
        }
    }

    private fun applyTeam(
        post: FindTeammatePost,
        requestMember: Member,
    ): Long {
        check(post.canApply) { "팀원 모집이 마감되었습니다." }

        val teamOwner = memberReader.findById(post.authorId)
        val chattingRoom = chattingService.createOrGetChattingRoom(requestMember, teamOwner)

        if (!applyService.isApplied(requestMember.id!!, post.id!!)) {
            applyService.apply(requestMember.id, post.id)
            chattingService.sendFirstMetChat(post, chattingRoom, requestMember)
        }

        return chattingRoom.id!!
    }

    private fun inviteTeammate(
        post: FindTeamPost,
        requestMember: Member,
    ): Long {
        check(post.canApply) { "팀 모집이 마감되었습니다." }

        val teamFindMember = memberReader.findById(post.authorId)
        val chattingRoom = chattingService.createOrGetChattingRoom(teamFindMember, requestMember)

        if (!applyService.isApplied(teamFindMember.id!!, post.id!!)) {
            applyService.apply(teamFindMember.id, post.id)
        }

        return chattingRoom.id!!
    }
}
