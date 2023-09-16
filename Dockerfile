FROM openjdk:11-slim-buster

WORKDIR /app

ARG ORIGINAL_JAR_FILE=./build/libs/admin-service-1.0.0.jar

COPY ${ORIGINAL_JAR_FILE} admin-service.jar

CMD ["java", "-jar", "/app/admin-service.jar"]
