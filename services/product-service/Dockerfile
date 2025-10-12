# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# build inside the image (no host tooling needed)
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package
    
# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy

ENV TZ=UTC \
    JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE=prod

WORKDIR /app
VOLUME /tmp

COPY --from=build /workspace/target/*.jar /app/app.jar

RUN useradd -r -u 10001 spring
USER 10001

EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
    