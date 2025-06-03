FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/garageapi-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env
ENTRYPOINT ["java", "-jar", "app.jar"]
