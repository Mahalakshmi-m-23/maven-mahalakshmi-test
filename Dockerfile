# Stage 1: Build and package the Maven JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/maven-mahalakshmi-0.0.1-SNAPSHOT.jar app.jar

CMD ["echo", "Maven Library Management System container built successfully!"]