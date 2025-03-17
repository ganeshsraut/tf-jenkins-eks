FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
#RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests
# Final stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar /inventory-service.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "/inventory-service.jar" ]
