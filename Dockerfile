FROM openjdk:17
WORKDIR /app
COPY build/libs/my-application.jar /app/application.jar
CMD ["java", "-jar", "application.jar"]
