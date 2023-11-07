# Zserio Service REST backend

[![](https://github.com/ndsev/zserio-service-rest-java/actions/workflows/build_linux.yml/badge.svg)](https://github.com/ndsev/zserio-service-rest-java/actions/workflows/build_linux.yml)
[![](https://github.com/ndsev/zserio-service-rest-java/actions/workflows/build_windows.yml/badge.svg)](https://github.com/ndsev/zserio-service-rest-java/actions/workflows/build_windows.yml)
[![](https://img.shields.io/github/watchers/ndsev/zserio-service-rest-java.svg)](https://GitHub.com/ndsev/zserio-service-rest-java/watchers)
[![](https://img.shields.io/github/forks/ndsev/zserio-service-rest-java.svg)](https://GitHub.com/ndsev/zserio-service-rest-java/network/members)
[![](https://img.shields.io/github/stars/ndsev/zserio-service-rest-java.svg?color=yellow)](https://GitHub.com/ndsev/zserio-service-rest-java/stargazers)

--------

Sample implementation of Zserio Service REST backend in **Java** using SpringBoot.

# Prerequisites

   1. JDK 1.8
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