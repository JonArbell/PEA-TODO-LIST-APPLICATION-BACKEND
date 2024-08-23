FROM eclipse-temurin:21-jdk
COPY . .
RUN mvn clean package -DskipTests


# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim


# Copy the JAR file from the target directory in the build context to the container
COPY PeaTodoListApplication/target/PeaTodoListApplication-0.0.1-SNAPSHOT.jar PeaTodoListApplication.jar

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "PeaTodoListApplication.jar"]