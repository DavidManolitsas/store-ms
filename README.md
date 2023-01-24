# Store Microservice

This project is a mock storefront used to demonstrate the functionality and use cases for Java Streams. 

## Setup

Set the following environment variables before the Build and Run steps:

```bash
export CLIENT_ID=""
export CLIENT_SECRET=""
export DATABASE_PWD=""
```


## Build

Build the project with Maven:

```bash
mvn clean sortpom:sort fmt:format install
```

or

Build the project with Docker:
```bash
docker build -t store-ms \
--build-arg CLIENT_ID=${CLIENT_ID} \
--build-arg CLIENT_SECRET=${CLIENT_SECRET} .
```

## Run

Run the `.jar` package:

```bash
java -jar ./target/store-ms-1.jar
```

or

Run with Docker:

```bash
docker run -d -p 8080:8080 \
-e DATABASE_PWD=${DATABASE_PWD} \
store-ms
```

## Test

You can test the service's status with the following curl command:

```bash
curl -i http://localhost:8080/actuator/health
```
