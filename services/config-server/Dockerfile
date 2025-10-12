# ---------- build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# ---------- runtime ----------
FROM eclipse-temurin:17-jre-jammy
ENV TZ=UTC \
    SPRING_PROFILES_ACTIVE=native \
    JAVA_OPTS=""

RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app
VOLUME /tmp
COPY --from=build /workspace/target/*.jar /app/app.jar
# non-root
RUN useradd -r -u 10001 spring
USER 10001
EXPOSE 8071
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
    