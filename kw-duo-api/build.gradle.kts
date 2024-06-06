tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":support:logging"))
    implementation(project(":support:jwt"))
    implementation(project(":support:util"))
    implementation(project(":storage:db"))
    implementation(project(":storage:api"))
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:sockjs-client:1.0.2")
    implementation("org.webjars:stomp-websocket:2.3.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    testImplementation("org.springframework.security:spring-security-test")
}
