FROM openjdk:11
LABEL version="1.0"
LABEL description="Registry Service Image"
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8761