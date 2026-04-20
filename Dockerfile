# Stage 1: Build de l'application avec Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copie les fichiers Maven
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Copie le code source
COPY src ./src

# Build l'application
RUN mvn clean package -DskipTests

# Stage 2: Runtime avec Java 21
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copie le JAR depuis l'étape de build
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose le port Spring Boot
EXPOSE 8080

# Lance l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

