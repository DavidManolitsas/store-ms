FROM openjdk:11

VOLUME /app
COPY target/java-streams-1.0.0.jar /app/java-streams-1.0.0.jar
WORKDIR /app
CMD java -jar java-streams-1.0.0.jar