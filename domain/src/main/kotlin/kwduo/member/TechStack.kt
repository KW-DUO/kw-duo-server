package kwduo.member

enum class TechStack(
    val displayName: String,
    val value: String,
) {
    REACT("React", "REACT"),
    NEXTJS("Next.js", "NEXTJS"),
    JAVASCRIPT("JavaScript", "JAVASCRIPT"),
    TYPESCRIPT("TypeScript", "TYPESCRIPT"),
    SPRING("Spring", "SPRING"),
    NODEJS("Node.js", "NODEJS"),
    NESTJS("Nest.js", "NESTJS"),
    FLASK("Flask", "FLASK"),
    DJANGO("Django", "DJANGO"),
    MYSQL("MySQL", "MYSQL"),
    MONGODB("MongoDB", "MONGODB"),
    REDIS("Redis", "REDIS"),
    KOTLIN("Kotlin", "KOTLIN"),
    JAVA("Java", "JAVA"),
    SWIFT("Swift", "SWIFT"),
    FLUTTER("Flutter", "FLUTTER"),
    REACT_NATIVE("React Native", "REACT_NATIVE"),
    PYTHON("Python", "PYTHON"),
    TENSORFLOW("TensorFlow", "TENSORFLOW"),
    PYTORCH("PyTorch", "PYTORCH"),
    UNITY("Unity", "UNITY"),
    UNREAL("Unreal Engine", "UNREAL"),
    C("C", "C"),
    CPP("C++", "CPP"),
    CSHARP("C#", "CSHARP"),
    ;

    companion object {
        fun of(value: String): TechStack {
            return entries.first { it.value == value }
        }
    }
}
