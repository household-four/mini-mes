# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY . .

ARG SERVICE_PATH
RUN mvn -q -DskipTests -pl ${SERVICE_PATH} -am package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

ARG SERVICE_PATH
COPY --from=build /workspace/${SERVICE_PATH}/target/*SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
    