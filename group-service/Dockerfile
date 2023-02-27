FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} group-service.jar
ENTRYPOINT ["java", "-jar", "/group-service.jar"]
EXPOSE 8082