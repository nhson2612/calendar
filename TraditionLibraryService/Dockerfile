FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/TraditionLibraryService-0.0.1-SNAPSHOT.jar tradition-library-service.jar
EXPOSE 8084

ENTRYPOINT [ "java", "-jar", "tradition-library-service.jar"]