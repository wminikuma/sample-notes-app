# 빌드 스테이지
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle .
COPY src src
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# 실행 스테이지
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod","-jar", "app.jar"]