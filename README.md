# Zserio Service REST backend

Sample implementation of Zserio Service REST backend in **Java** using SpringBoot.

# Prerequisites

   1. JDK 11+
   2. Maven 3.6

# Usage

## Build Calculator example

```bash
mvn package
```

## Start server of Calculator example

```bash
java -jar calculator-server/target/calculator-server-0.0.1-SNAPSHOT.jar
# press Ctrl+C to quit the server
```

## Start client of Calculator example

```bash
java -jar calculator-client/target/calculator-client-0.0.1-SNAPSHOT.jar
# follow client's instructions
# ...
# press q + ENTER to quit the client
```

## URL for OpenAPI Swagger UI

```bash
http://localhost:5000/api-ui.html
```

## URL for OpenAPI docs (JSON format)

```bash
http://localhost:5000/api-docs
```