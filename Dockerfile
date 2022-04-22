FROM openjdk:11

VOLUME /app
COPY target/store-ms-1.jar /app/store-ms-1.jar
WORKDIR /app
CMD java -jar store-ms-1.jar