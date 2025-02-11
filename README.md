# Restaurant Application

[Go to Turkish README](README_TR.md)

## Project Architecture and System Design

[![Restaurant Application Architecture](./img/project-design.png)](./img/project-design.png)

Restaurant App is a comprehensive project that I developed on my own. This application is built using a microservices architecture and consists of five main modules: Eureka-server, api-gateway, user-service, restaurant-service, and logging-service. The Discovery Server serves as a service registry that enables effective communication between microservices. The API Gateway serves as the single entry point for all client requests and forwards them to the appropriate service. The User Service manages users and their reviews for restaurants, providing functions such as user registration, posting reviews, and personalized restaurant recommendations based on user location and restaurant ratings. The Restaurant Service manages all operations related to restaurants, including restaurant registration and listing, and stores all restaurant data in Elastic Search. The Logging Service is a logging service for a Spring Boot application. It uses Apache Kafka for message consumption and MongoDB for data storage. The service is designed to consume error logs from Kafka, store them in MongoDB, and provide an API for retrieving the logs. Each service in this repository has its own README.md file, providing detailed information about the project.

### Tech Stack

---
- Java 17
- Spring Boot
- Spring Data JPA
- OpenAPI documentation
- Docker
- Docker compose
- React for frontend

## Services

- [Eureka Server](./eureka-service/README.md): A Spring Boot application that provides service discovery and registration. It uses Netflix Eureka to register and discover services. The Discovery Service is the single entry point for all clients. It forwards requests to the appropriate service.

- [API Gateway](./api-gateway/README.md): A Spring Boot application that provides a RESTful API for managing users and restaurants. It includes operations for creating, retrieving, updating, and deleting users and restaurants. The API Gateway is the single entry point for all clients. It forwards requests to the appropriate service. It also provides a way to authenticate and authorize users.

- [User Service](./user-service/README.md): A Spring Boot application that provides a RESTful API for managing users and user reviews. It includes operations for creating, retrieving, updating, and deleting users and user reviews. Each user has associated latitude and longitude values. User reviews have a score between 1 and 5.

- [Restaurant Service](./restaurant-service/README.md): A Spring Boot application that provides a RESTful API for managing restaurants. It includes operations for creating, retrieving, updating, and deleting restaurants. Each restaurant has associated latitude and longitude values. The data for the restaurants is stored in ElasticSearch and queries are also made to ElasticSearch. The service also provides an API for recommending restaurants to users based on their location and the restaurant's score.

- [Logging Service](./logging-service/README.md): This is a logging service for a Spring Boot application. It uses Apache Kafka for message consumption and MongoDB for data storage. The service is designed to consume error logs from Kafka, store them in MongoDB, and provide an API for retrieving the logs.

## Docker Deployment

This project uses Docker to run each microservice and the databases they depend on. This allows the project to be easily run on any platform. Below are the steps to run the Dockerized version of the project.

### Prerequisites

- Ensure Docker and Docker Compose are installed. For instructions on how to install Docker and Docker Compose, refer to the [official Docker documentation](https://docs.docker.com/get-docker/).

### Running Steps

1. Clone or download the project to your local machine.

2. Open a terminal and navigate to the root directory of the project.

3. Run the following command:

    ```bash
    docker-compose up -d
    ```

   This command starts all services and databases defined in the Docker Compose file.

4. To check that all containers are running properly, run the following command:

    ```bash
    docker ps
    ```

   This command gives a list of all running Docker containers. You should see a container running for each microservice and database.

### Checking Container Status

You can use the `docker ps` command to check the status of each container. This command gives a list of all running Docker containers. You should see a container running for each microservice and database.

### Accessing Services

Each microservice is made accessible over a specific port. Below is a list of which port each microservice runs on:

- `eureka-service`: 8761
- `api-gateway`: 8080
- `restaurant-service`: 8081
- `user-service`: 8082
- `logging-service`: 8083

Additionally, the databases and other services can be accessed over the following ports:

- `postgres_db`: 5432
- `mongo_db`: 27017
- `elasticsearch`: 9200
- `zipkin`: 9411
- `kafka`: 9092
- `zookeeper`: 2181

### Stopping Containers

You can use the following command to stop all containers:

```bash
docker-compose down
```

## Contact

### Seyid Ahmet ARVAS

<a href="https://github.com/ahmetarvastr" target="_blank">
<img  src=https://img.shields.io/badge/github-%2324292e.svg?&style=for-the-badge&logo=github&logoColor=white alt=github style="margin-bottom: 20px;" />
</a>
<a href = "mailto:example@gmail.com?subject = Feedback&body = Message">
<img src=https://img.shields.io/badge/send-email-email?&style=for-the-badge&logo=microsoftoutlook&color=CD5C5C alt=gmail style="margin-bottom: 20px; margin-left:20px" />
</a>
<a href="https://linkedin.com/in/seyidahmetarvas" target="_blank">
<img src=https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 20px; margin-left:20px" />
</a>  

## Seyid Ahmet ARVAS - Restaurant Project

<div align="center">
  <img src="./img/java.png" alt="Logo" width="220" height="140">
<h3 align="center">Java</h3>
</div>

<div align="center">
  <img src="./img/spring.png" alt="Logo" width="220" height="140">
<h3 align="center">SpringBoot</h3>   
</div>