# Start from a JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven build files first (to leverage Docker cache)
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy full source
COPY . .

# Build the application
RUN ./mvnw clean install -DskipTests

# Run the JAR file
CMD ["java", "-jar", "target/*.jar"]
