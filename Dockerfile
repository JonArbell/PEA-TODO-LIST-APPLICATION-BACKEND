# Use a base image that includes Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Use a smaller base image for the runtime environment
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/PeaTodoListApplication-0.0.1-SNAPSHOT.jar PeaTodoListApplication.jar

# Expose the application port
EXPOSE 8080

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "PeaTodoListApplication.jar"]


ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/pea_db
ENV SPRING_DATASOURCE_USERNAME=arbellOwner
ENV SPRING_DATASOURCE_PASSWORD=$2a$10$uDdEvKlDjhgyJGPmDFMl6.JGGJmXi3asxaLOEo4qWrE2cs.d8rdAq
