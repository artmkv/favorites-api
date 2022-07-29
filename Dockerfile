FROM openjdk:11.0-jdk-slim
COPY . .
EXPOSE 8071
CMD ["java", "-jar", "/build/libs/favorites-api-0.0.1-SNAPSHOT.jar"]