package kwduo.post

import kwduo.apply.ApplyService
import kwduo.member.MemberService
import kwduo.member.dto.MemberInfo
import org.springframework.stereotype.Service

@Service
class PostApplicantService(
    private val applyService: ApplyService,
    private val memberReader: MemberService,
) {
    fun getApplicants(postId: Long): List<MemberInfo> {
        val applicants = applyService.getApplicants(postId)

        return applicants.map {
            memberReader.getMemberInfo(it.memberId)
        }
    }
}
