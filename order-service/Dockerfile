FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace/app
COPY . .

RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /workspace/app
COPY --from=build /workspace/app/target/order-service-0.0.1-SNAPSHOT.jar /workspace/app
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]
