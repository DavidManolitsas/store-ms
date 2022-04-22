# Store Microservice

This project is a mock storefront used to demonstrate the functionality and use cases for Java Streams. 
Additionally, it has also been developed with the intention for it to be secured behind a 
Kong API Gateway with more information found on the 
[store-kong-api-gateway](https://github.com/DavidManolitsas/store-kong-api-gateway) project.


## Build and Run Project in a Docker container behind Kong API Gateway

To build the Docker container run the following Docker commands:
```bash
mvn clean sortpom:sort fmt:format install
docker build -t store-ms .
```

To run the project behind Kong API Gateway execute, follow the Build and Run instructions from the
[store-kong-api-gateway](https://github.com/DavidManolitsas/store-kong-api-gateway) project

Alternatively, to run the project in a standalone Docker container, execute:
```bash
docker run -d -p 8080:8080 store-ms
```

## Build and Run Project Locally

To sort `pom.xml`,  format code  and build the project run the following command:
```bash
mvn clean sortpom:sort fmt:format install
```

To run the project locally, execute the following command:
```bash
mvn spring-boot:run
```