# Build Stage
FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar movewise-api.jar
COPY .env .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "movewise-api.jar"]