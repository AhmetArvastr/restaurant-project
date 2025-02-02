# Eureka Service

[Go to Turkish README](README_TR.md)

The Eureka Service is a key component in our microservice architecture. It allows for the registration of microservices and enables service eureka, which is essential for load balancing and failover of services.

## Key Features

- Registers all microservices.
- Enables service discovery for load balancing and failover.
- Integrates with Spring Boot and Eureka.

## Configuration

The configuration of the Eureka Service is found in the `application.yml` file. This file specifies the port the service will run on and the Eureka client configuration.

## Docker

This service is packaged to run on Docker. The Dockerfile specifies how the Docker image will be built.

## Running

You can use the following commands to build and run the Docker image:

```bash
docker build -t Eureka-service .
docker run -p 8761:8761 discovery-service
```

These commands first build the Docker image and then run the eureka-service on port 8761.

## Dependencies

- **Spring Boot:** The Discovery Service is built using Spring Boot.
- **Eureka Server:** The Discovery Service uses the Eureka Server for service registration and eureka.

Please note that all microservices should register with the Discovery Service for service discovery to function correctly.

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
