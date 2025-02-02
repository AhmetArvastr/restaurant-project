
# API Gateway Service

[Go to Turkish README](README_TR.md)

The API Gateway is the central entry point for all services in our microservice architecture. This service routes incoming requests to the relevant microservices.

## Key Features

- Routes to Restaurant and User services.
- Integrates with Eureka Server.
- Provides tracing support with Zipkin.

## Configuration

The configuration of the API Gateway is found in the `application.yml` file. This file specifies the port the service will run on, which services it will route to, and how it will integrate with Eureka and Zipkin.

## Docker

This service is packaged to run on Docker. The `Dockerfile` specifies how the Docker image will be built.

## Running

You can use the following commands to build and run the Docker image:

```bash
docker build -t api-gateway .
docker run -p 8080:8080 api-gateway
```

These commands first build the Docker image and then run the `api-gateway` service on port 8080.

## Dependencies

- Restaurant Service: The API Gateway routes requests to the Restaurant Service at the `/api/v1/restaurants/**` path.
- User Service: The API Gateway routes requests to the User Service at the `/api/v1/users/**` path.
- Eureka Service: The API Gateway integrates with the Eureka Server for service eureka.
- Zipkin: The API Gateway integrates with Zipkin for tracing.

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

