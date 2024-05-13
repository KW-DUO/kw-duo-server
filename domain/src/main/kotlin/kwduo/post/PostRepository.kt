package kwduo.post

interface PostRepository {
    fun save(post: Post): Post

    fun saveFindTeammatePost(post: FindTeammatePost): FindTeammatePost

    fun saveFindTeamPost(post: FindTeamPost): FindTeamPost

    fun findById(id: Long): Post?
}
