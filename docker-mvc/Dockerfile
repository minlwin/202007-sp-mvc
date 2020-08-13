FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY target/docker-mvc-1.0.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profile.active=docker", "/app.jar"]