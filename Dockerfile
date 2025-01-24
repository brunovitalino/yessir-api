FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /buildfiles
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN ["mvn", "clean", "package", "-Dmaven.test.skip"]

FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=/buildfiles/target/*jar
COPY --from=build ${JAR_FILE} yessir-api.jar
EXPOSE 80
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "yessir-api.jar"]

# mvn clean package -Dmaven.test.skip
# java -Dspring.profiles.active=default -jar target/*jar
