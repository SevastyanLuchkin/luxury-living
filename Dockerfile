FROM openjdk:17-alpine
MAINTAINER luxury
COPY living-0.0.3-SNAPSHOT.jar luxury.jar
ENTRYPOINT ["java","-jar","/luxury.jar"]