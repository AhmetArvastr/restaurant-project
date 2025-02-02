# Logging Service

[Go to Turkish README](README_TR.md)

This is a logging service for a Spring Boot application. It uses Apache Kafka for message consumption and MongoDB for data storage. The service is designed to consume error logs from Kafka, store them in MongoDB, and provide an API for retrieving the logs.

## Project Structure

The project is structured into four main packages:

1. `com.seyidahmetarvas.loggingservice.controller`: This package contains the `ErrorLogController` class which exposes a REST API for retrieving the error logs.

2. `com.seyidahmetarvas.loggingservice.repository`: This package contains the `LogNotificationRepository` interface which extends `MongoRepository`. This interface is used for CRUD operations on the `LogNotification` documents in MongoDB.

3. `com.seyidahmetarvas.loggingservice.service`: This package contains the `KafkaConsumerService` class which consumes messages from a Kafka topic and saves them as `LogNotification` documents in MongoDB.

4. `com.seyidahmetarvas.loggingservice.model`: This package contains the `LogNotification` class which represents the error log documents in MongoDB.

## Usage

### Kafka Consumer

The `KafkaConsumerService` class is annotated with `@Service' (for logging). It has a method `consume` annotated with `@KafkaListener`, which consumes messages from the Kafka topic "errorLog". The consumed message is saved as an `LogNotification` document in MongoDB.

### Log Notification Repository

The `LogNotificationRepository` interface extends `MongoRepository<LogNotification, String>`, which provides methods for CRUD operations on `LogNotification` documents in MongoDB.

### Error Log Controller

The `ErrorLogController` class is annotated with `@RestController` and `@RequestMapping("/api/v1/error-logs")`. It has a method `findAll` annotated with `@GetMapping`, which retrieves all `LogNotification` documents from MongoDB and returns them as a list.

### Error Log Entity

The `LogNotification` class is annotated with `@Document(collection = "errorlogdoc")`. It represents the error log documents in MongoDB.

## Setup

To use this service, you need to have a running instance of Apache Kafka and MongoDB. You also need to configure the Kafka topic and MongoDB connection details in the application's properties file.

## Running the Application

You can run the application using the following command:

```bash
mvn spring-boot:run
```

Once the application is running, you can access the API at `http://localhost:8080/api/v1/error-logs`.

## Contact

### Seyid Ahmet ARVAS

<a href="https://github.com/ahmetarvastr" target="_blank">
<img  src=https://img.shields.io/badge/github-%2324292e.svg?&style=for-the-badge&logo=github&logoColor=white alt=github style="margin-bottom: 20px;" />
</a>
<a href = "mailto:example@outlook.com?subject = Feedback&body = Message">
<img src=https://img.shields.io/badge/send-email-email?&style=for-the-badge&logo=microsoftoutlook&color=CD5C5C alt=gmail style="margin-bottom: 20px; margin-left:20px" />
</a>
<a href="https://linkedin.com/in/seyidahmetarvas" target="_blank">
<img src=https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 20px; margin-left:20px" />
</a>  

## Seyid Ahmet ARVAS - Restaurant Project

<div align="center">
<img src="../img/java.png" alt="Logo" width="220" height="140">
<h3 align="center">Java</h3>
</div>

<div align="center">
<img src="../img/spring.png" alt="Logo" width="220" height="140">
<h3 align="center">Spring</h3>   
</div>
