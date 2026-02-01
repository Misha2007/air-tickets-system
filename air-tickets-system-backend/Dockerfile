FROM openjdk:21-ea-1-jdk

WORKDIR /app

COPY target/air-tickets-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD [ "java", "-jar", "app.jar" ]