# MSVC-Customer

## Description
Bank customer management microservice developed as part of a microservices architecture for a banking system. It implements personal and business customer management with their respective business rules and validations.

## Architecture
- **Domain-Driven Design (DDD)**: Rich domain with entities, value objects, and business rules
- **Hexagonal Architecture**: Clear separation between domain, application, and infrastructure
- **Clean Architecture**: SOLID principles and separation of responsibilities

## Technologies
- **Spring Boot 2.7.8** with WebFlux for reactive operations
- **MongoDB** with Spring Data MongoDB Reactive
- **Java 11** with Lombok for boilerplate code reduction
- **Maven** for dependency management

## Features
- Personal and business client management
- Specific business validations by client type
- Reactive persistence with MongoDB
- Reactive REST API with WebFlux
- Audit and timestamp management
- Document validations (DNI, RUC)

## Features
- ✅ Create a personal client
- ✅ Create a business client
- ✅ View clients by ID
- ✅ List all clients of a type
- ✅ Deactivate clients
- ✅ Business rule validations
- ✅ Change auditing
