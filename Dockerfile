# Start with a base image containing Maven and OpenJDK 11
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Create a new image containing only the JRE
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the previous stage
COPY --from=build /app/target/system-0.0.1-SNAPSHOT.jar /app/AirManagementSystem.jar

# Copy wait-for-it.sh script
COPY wait-for-it.sh /app/wait-for-it.sh

# Grant execute permissions to the script
RUN chmod +x /app/wait-for-it.sh

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run the Spring Boot application with the prod profile, using the wait-for-it.sh script
CMD ["./wait-for-it.sh", "db:3306", "--timeout=60", "--", "java", "-jar", "-Dspring.profiles.active=prod", "AirManagementSystem.jar"]
