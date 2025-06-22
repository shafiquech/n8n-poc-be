# Stage 1: Build the application using Maven
FROM maven:3.9.3-eclipse-temurin-17 as builder

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a slim JDK
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
