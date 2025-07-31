FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/AuthService-0.0.1-SNAPSHOT.jar auth-service.jar
EXPOSE 9000

ENTRYPOINT [ "java", "-jar", "auth-service.jar" ]
