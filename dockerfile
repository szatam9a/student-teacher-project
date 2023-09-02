FROM eclipse-temurin:17

COPY target/seikai-backend.jar /app/seikai-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app/seikai-backend.jar"]