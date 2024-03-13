FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./mvnw spring-boot:run

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/living-0.0.8-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]