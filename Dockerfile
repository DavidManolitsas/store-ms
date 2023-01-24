FROM maven:3.8.7-amazoncorretto-11 as builder
ARG CLIENT_ID
ARG CLIENT_SECRET
ENV CLIENT_ID=${CLIENT_ID}
ENV CLIENT_SECRET=${CLIENT_SECRET}
WORKDIR /app
ADD . .
RUN mvn clean sortpom:sort fmt:format install

FROM amazoncorretto:11 as runtime
ENV DATABASE_PWD=${DATABASE_PWD}
WORKDIR /app
COPY --from=builder /app/target/store-ms-1.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
