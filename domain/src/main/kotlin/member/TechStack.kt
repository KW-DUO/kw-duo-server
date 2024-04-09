package member

enum class TechStack(
    val displayName: String
) {
    REACT("React"),
    NEXTJS("Next.js"),
    JAVASCRIPT("JavaScript"),
    TYPESCRIPT("TypeScript"),
    SPRING("Spring"),
    NODEJS("Node.js"),
    NESTJS("Nest.js"),
    FLASK("Flask"),
    DJANGO("Django"),
    MYSQL("MySQL"),
    MONGODB("MongoDB"),
    REDIS("Redis"),
    KOTLIN("Kotlin"),
    JAVA("Java"),
    SWIFT("Swift"),
    FLUTTER("Flutter"),
    REACT_NATIVE("React Native"),
    PYTHON("Python"),
    TENSORFLOW("TensorFlow"),
    PYTORCH("PyTorch"),
    UNITY("Unity"),
    UNREAL("Unreal Engine"),
    C("C"),
    CPP("C++"),
    CSHARP("C#"),
    ;

    companion object {
        fun of(displayName: String): TechStack {
            return entries.first { it.displayName == displayName }
        }
    }
}