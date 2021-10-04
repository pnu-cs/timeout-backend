FROM openjdk:11
COPY target/*.jar timeout.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "timeout.jar"]
