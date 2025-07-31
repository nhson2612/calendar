FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/LunarCalendarService-0.0.1-SNAPSHOT.jar lunar-calendar-service.jar
EXPOSE 8103

ENTRYPOINT [ "java", "-jar", "lunar-calendar-service.jar" ]