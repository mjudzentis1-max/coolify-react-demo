FROM node:22-alpine AS frontend-build

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
COPY index.html vite.config.js ./
COPY src ./src
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-21 AS backend-build

WORKDIR /app
COPY backend/pom.xml ./pom.xml
COPY backend/src ./src
COPY --from=frontend-build /app/dist ./src/main/resources/static
RUN mvn --batch-mode package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=backend-build /app/target/forma-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
