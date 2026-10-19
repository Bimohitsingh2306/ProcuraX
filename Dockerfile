FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY target/procuraX-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]