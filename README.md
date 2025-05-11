# PBI Board (In Progress)

PBI Board is a microservices-based project developed with Spring Boot, inspired by sprint planning tools like Azure Boards. It focuses on sprint management, task tracking, and workflow organization. The project also serves as a learning platform for distributed system patterns and scalable architecture.

## Features

- Task tracking and sprint planning
- Workflow boards for organizing tasks
- Modular design with independent microservices
- Designed to experiment with real-world architecture patterns

## Tech Stack & Architecture

- **Spring Boot (Microservices)**: Separate services for Task, User, and Board
- **API Gateway**: Central entry point for routing, security, and load balancing
- **Database per Service**: Each service uses its own isolated database (PostgreSQL or MongoDB)
- **CQRS**: Separates read/write responsibilities in services like Task and Board
- **Outbox Pattern**: Ensures reliable event publishing for consistency
- **Saga Pattern (Choreography)**: Coordinates distributed workflows without tight coupling
- **Kafka**: Enables asynchronous communication between services
- **Docker**: Used for containerized development and deployment
- **Spring Cloud Config**: Centralized configuration and potential service discovery

## Inspiration

The project is inspired by Azure Boards, both in terms of feature set and user experience. It intentionally includes architectural patterns to deepen understanding of scalable distributed systems.
