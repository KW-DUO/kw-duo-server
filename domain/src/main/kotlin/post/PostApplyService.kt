package post

import apply.ApplyService
import chatting.ChattingService
import member.Member
import member.MemberReader
import member.MemberService

class PostApplyService(
    private val applyService: ApplyService,
    private val chattingService: ChattingService,
    private val memberReader: MemberReader,
) {
    fun applyTeam(post: FindTeammatePost, requestMember: Member) {
        check(post.canApply) { "팀원 모집이 마감되었습니다." }

        applyService.apply(requestMember.id!!, post.id!!)

        val teamOwner = memberReader.findById(post.authorId)
        val chattingRoom = chattingService.createOrGetChattingRoom(requestMember, teamOwner)

        chattingService.sendFirstMetChat(post, chattingRoom, requestMember)
    }
}