allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    compileOnly(project(":domain"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
//    runtimeOnly("com.mysql:mysql-connector-j")
//    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
}
