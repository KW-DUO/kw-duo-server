package kwduo.post

enum class ProjectType {
    SIDE_PROJECT,
    CLASS_PROJECT,
    GRADUATION_PROJECT,
    ;

    companion object {
        fun of(projectType: String): ProjectType {
            return entries.first { it.name == projectType }
        }
    }
}