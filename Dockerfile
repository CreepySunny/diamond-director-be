# Use official Gradle 8.5.0 with OpenJDK 17 image as the base image
FROM gradle:8.5.0-jdk17-alpine

# Set working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY src src


RUN gradle clean

# Build the Spring Boot application using Gradle
RUN gradle bootJar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "build/libs/Diamond_Director_BE-0.0.1.jar"]