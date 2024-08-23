FROM eclipse-temurin:21-jdk
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/PeaTodoListApplication-0.0.1-SNAPSHOT.jar /app/PeaTodoListApplication.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","PeaTodoListApplication.jar"]


