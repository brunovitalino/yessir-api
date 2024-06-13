FROM openjdk:21-jdk-slim
EXPOSE 8080
ADD /target/yessir-0.0.1-SNAPSHOT.jar yessir.jar
ENTRYPOINT ["java", "-jar", "yessir.jar"]
