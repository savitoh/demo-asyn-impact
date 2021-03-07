# Async Impact
DEMO to assess the performance impact of asynchronous methods in Spring Boot applications using background thread pool.

## Requeriments to start UP

- JDK 11
- Maven v3.6+

## How to run

This project can be carried out in two different ways.

1. Maven:

   In the project's root folder run the maven command below for the project to be built:
    ```
    mvn clean build
    ```
   Now in the target folder:
   ```
   java -jar demo-async-impact-0.0.1-SNAPSHOT.jar
    ```
   Feel free to change the ThreadPoolTaskExecutor configuration values


2. Docker: