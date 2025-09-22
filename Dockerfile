FROM ubuntu:latest
LABEL authors="amigaura"

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/demo-rabbitmq-to-kafka-0.0.1-SNAPSHOT.jar app.jar

# Copy the jaas.conf file into the container
COPY jaas.conf jaas.conf

# Set environment variables
# ENV OAUTH_TOKEN_ENDPOINT_URI=https://cloudsso-test.cisco.com/as/token.oauth2
# ENV OAUTH_CLIENT_ID=smart_receiver_nprd
# ENV OAUTH_CLIENT_SECRET=MTU0MDMxODE5NTExNw%3D%3D
# ENV OAUTH_USERNAME=amigaura
# ENV OAUTH_PASSWORD=Bunty@123

# Expose the port the application runs on
EXPOSE 8080

# Run the jar file
# ENTRYPOINT ["java", "-Djava.security.auth.login.config=jaas.conf", "-jar", "app.jar"]
ENTRYPOINT ["sh", "-c", "java -Djava.security.auth.login.config=jaas.conf -jar app.jar"]