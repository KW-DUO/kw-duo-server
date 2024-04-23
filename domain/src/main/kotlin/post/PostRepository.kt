package post

interface PostRepository {
    fun save(post: Post)

    fun findById(id: Long): Post?

    fun findAll(): List<Post>
}
