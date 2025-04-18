FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/ceprevirtualbackend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_ceprevirtualbackend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_ceprevirtualbackend.jar"]