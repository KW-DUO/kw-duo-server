package kwduo.post

interface PostRepository {
    fun save(post: Post): Post

    fun findById(id: Long): Post?

    fun findAll(): List<Post>
}
