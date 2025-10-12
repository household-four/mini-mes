# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml pom.xml
COPY services/common-domain/pom.xml services/common-domain/pom.xml
COPY services/config-server/pom.xml services/config-server/pom.xml
COPY services/product-service/pom.xml services/product-service/pom.xml
COPY services/planning-service/pom.xml services/planning-service/pom.xml

RUN mvn -q -DskipTests -pl services/product-service -am dependency:go-offline

COPY . .
RUN mvn -q -DskipTests -pl services/product-service -am package
    
# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/services/product-service/target/product_service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
    