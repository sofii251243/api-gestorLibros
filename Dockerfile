# Docker configuration for BookLog API
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/api-movil-gestor-libros-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

