FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} yessir.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "yessir.jar"]
