FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/living-0.0.3-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
