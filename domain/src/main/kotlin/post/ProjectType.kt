package post

enum class ProjectType(
    private val value: String
) {
    SIDE_PROJECT("SIDE_PROJECT"),
    CLASS_PROJECT("CLASS_PROJECT"),
    GRADUATION_PROJECT("GRADUATION_PROJECT"),
    ;

    companion object {
        fun of(projectType: String): ProjectType {
            return entries.first { it.value == projectType }
        }
    }
}