FROM gradle:8.5.0-jdk17-alpine

WORKDIR /app

#  Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Build the application
RUN gradle bootJar

# Expose the port
EXPOSE 8080

# Command to run
CMD ["java", "-jar", "build/libs/Diamond_Director_BE-0.0.1.jar"]