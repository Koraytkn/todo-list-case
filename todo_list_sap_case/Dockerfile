FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/todo_list_sap_case-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SERVER_PORT=8080

CMD ["java", "-jar", "app.jar"]
