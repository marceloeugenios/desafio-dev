FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=build/libs/parser-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} parser-app.jar

ENTRYPOINT ["java","-jar","parser-app.jar"]

EXPOSE 8080