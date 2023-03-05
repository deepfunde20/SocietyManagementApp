FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} jwt-service.jar
ENTRYPOINT ["java", "-jar", "/jwt-service.jar"]
EXPOSE 8085