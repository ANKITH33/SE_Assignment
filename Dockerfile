# Build image will run the runnable jar
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/imt2023075-todo-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
