FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=parser-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} parser.jar

ENTRYPOINT ["java","-jar","parser.jar"]

EXPOSE 8080