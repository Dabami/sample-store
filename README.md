# Immfly Backend Test

Immfly Java Backend technical test.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Testing](#testing)

---

## Features

- Endpoints to retrieve and create categories, products and orders.
- Endpoints to update, cancel and finish orders.
- Mock implementation of a payment gateway.
- Global exception handler.
- Automated API documentation using Swagger.
- Unit tests with Mockito and JUnit 5.
- Some clean code ans SOLID principles.

---

## Requirements

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- [Gradle 8](https://gradle.org/install/).

---

## Installation

1. Clone this repository:

    ```bash
    git clone https://gitlab.com/tests5354648/immfly.git
    cd immfly
    ```

2. Configure dependencies using Gradle:

    ```bash
    ./gradlew build
    ```

---

## Running the Application

1. Start the application:

    ```bash
    ./gradlew bootRun
    ```

2. Access the API documentation at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Testing

1. Run the tests:

```bash
./gradlew test
```
2. See the test report:

On Gitlab: https://immfly-4ece14.gitlab.io/

On local: build\reports\tests\test\index.html
