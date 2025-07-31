FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/DiscoveryServer-0.0.1-SNAPSHOT.jar discovery-server.jar
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
