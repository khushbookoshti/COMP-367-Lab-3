# Using a lightweight JDK base image
FROM openjdk:17-slim

# Setting the working directory in the container
WORKDIR /app

# Copying the packaged jar file into the container
COPY target/Lab2-Dev-Khushboo-0.0.1-SNAPSHOT.jar /app/app.jar

# Exposing the port the app runs on
EXPOSE 8080

# Running the jar file
CMD ["java", "-jar", "app.jar"]
