# Supplemental 6: Microservices Architecture ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/supplemental_6))

## Pre-work

Please review the following resources before lecture:

### Recommended
* [Microservices Explained in 5 Minutes (Video)](https://www.youtube.com/watch?v=lTAcCNbJ7KE)
* [Microservices Architecture Tutorial (Article)](https://www.geeksforgeeks.org/system-design/microservices/)
* [Microservices.io](https://microservices.io/) - Comprehensive microservices patterns catalog
* [Martin Fowler on Microservices](https://martinfowler.com/articles/microservices.html)

## Demo: StudyCRM Microservices Architecture

The **StudyCRM** project is a real-world example of microservices architecture that demonstrates how large applications can be broken into smaller, independent services.

### Repository

🔗 [code-differently/study-crm](https://github.com/code-differently/study-crm)

### What It Does

StudyCRM is a Customer Relationship Management (CRM) system split into three main services that work together:

- **Auth Service** - Handles user login and security
- **Entity Service** - Manages contacts and customer data
- **Organization Service** - Manages companies and teams

Each service has its own database and can be updated independently without breaking the others.

### Key Concepts Demonstrated

**Database Per Service** - Each microservice has its own database, so they don't interfere with each other.

**Service Communication** - Services talk to each other through APIs and message queues (Kafka).

**API Gateway** - A single entry point that routes requests to the right service.

**Containerization** - All services run in Docker containers for easy setup and deployment.

### Why This Is a Good Example

StudyCRM shows how microservices solve real problems: each service can be developed by different teams, deployed independently, and scaled based on demand. If one service goes down, the others keep running.

### Want to Learn More?

Check out the [High-Level Architecture](https://github.com/code-differently/study-crm/blob/main/docs/HIGH_LEVEL_ARCHITECTURE.md) documentation in the repository for a deeper dive into how everything works.

## Additional Resources

### Tools & Frameworks
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for building microservices in Java
- [Eventuate Tram](https://eventuate.io/docs/manual/eventuate-tram/latest/) - Framework for saga orchestration
- [Docker](https://www.docker.com/) - Containerization platform
- [Kubernetes](https://kubernetes.io/) - Container orchestration (for production deployments)
