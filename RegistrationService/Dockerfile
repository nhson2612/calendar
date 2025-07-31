FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/RegistrationService-0.0.1-SNAPSHOT.jar registration-service.jar
EXPOSE 8102

ENTRYPOINT [ "java", "-jar", "registration-service.jar" ]
