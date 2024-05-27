# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-slim
# Set the working directory in the container
#WORKDIR /app

# Copy the packaged jar file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transaction-service.jar
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/transaction-service.jar"]
ؤي