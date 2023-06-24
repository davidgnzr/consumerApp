FROM openjdk:17
EXPOSE 80
COPY /target/consumerApp.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]