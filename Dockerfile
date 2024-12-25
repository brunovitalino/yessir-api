FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} yessir-api.jar
EXPOSE 80
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "yessir-api.jar"]

# mvn clean package -Dmaven.test.skip
# java -Dspring.profiles.active=default -jar target/*jar
