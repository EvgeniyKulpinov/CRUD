FROM amazoncorretto:17.0.11
ARG JAR_FILE=CRUD-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]