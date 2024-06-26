FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

LABEL org.name="hezf"
#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /home/gradle/src/kw-duo-api/build/libs/kw-duo-api-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]