FROM openjdk:21-jdk-slim
EXPOSE 8080
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} yessir.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "yessir.jar"]
