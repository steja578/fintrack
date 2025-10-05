# Step 1 - Build the JAR using Maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2 - Create a lightweight runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
