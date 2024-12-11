FROM maven:3.8.3-amazoncorretto-17 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine3.16-jdk
EXPOSE 8083
COPY --from=build /usr/src/app/target/loggingservice-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]