FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/UserService-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8101

ENTRYPOINT [ "java", "-jar", "user-service.jar" ]
