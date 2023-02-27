FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} member-service.jar
ENTRYPOINT ["java", "-jar", "/member-service.jar"]
EXPOSE 8089