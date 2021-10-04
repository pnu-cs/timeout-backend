FROM openjdk:11
COPY target/*.jar timeout-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "timeout-0.0.1-SNAPSHOT.jar"]

