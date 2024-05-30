FROM openjdk:21-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transaction-service.jar
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/transaction-service.jar"]
