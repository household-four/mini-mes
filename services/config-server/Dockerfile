# ---------- build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml pom.xml
COPY services/common-domain/pom.xml services/common-domain/pom.xml
COPY services/config-server/pom.xml services/config-server/pom.xml
COPY services/product-service/pom.xml services/product-service/pom.xml
COPY services/planning-service/pom.xml services/planning-service/pom.xml
COPY services/execution-service/pom.xml services/execution-service/pom.xml
COPY services/gatewayserver/pom.xml services/gatewayserver/pom.xml

RUN mvn -q -DskipTests -pl services/config-server -am dependency:go-offline

COPY . .
RUN mvn -q -DskipTests -pl services/config-server -am package

# ---------- runtime ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/services/config-server/target/config_server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8071 
ENTRYPOINT ["java","-jar","/app/app.jar"]
    