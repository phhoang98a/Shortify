# Multi-stage build for efficiency and security
FROM gradle:8.5.0-jdk21 AS build

# Copy project files and build within the container
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Use a lightweight JRE base image for the final image
FROM openjdk:21-slim

# Create app directory and copy built JAR
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar

# Expose the application port
EXPOSE 8080

# Set entry point to start the application
ENTRYPOINT ["java", "-jar", "/app/application.jar"]