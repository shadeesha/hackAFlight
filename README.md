# hackAFlight

A scalable, microservice-ready Flight Booking Platform developed using Spring Boot. The system supports REST and GraphQL APIs, integrates RabbitMQ for asynchronous messaging, and leverages MySQL as the primary datastore. The entire application is deployed on AWS.

## 🚀 Features

- ✈️ Create, update, and manage airlines, airports, flights, and bookings
- 👤 Manage passengers and link them to flight bookings
- 🧾 RESTful APIs for easy integration
- 📦 GraphQL API for flexible queries
- 📩 Asynchronous communication with RabbitMQ
- 🗃️ MySQL for relational data persistence
- 📊 Spring Boot Actuator for system health and metrics
- ☁️ AWS EC2 deployment-ready
## 🛠️ Tech Stack

- **Java 22**
- **Spring Boot**
    - Spring Web (REST APIs)
    - Spring Data JPA (MySQL)
    - Spring Actuator
- **GraphQL** with `graphql-spring-boot-starter`
- **RabbitMQ** (messaging)
- **MySQL** (database)
- **H2** (database)
- **AWS EC2 and RDS** (cloud deployment)

├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/com/example/hackaflight/
│       │   ├── 📁 config/         # Configuration classes (e.g., RabbitMQ, CORS, Swagger, GraphQL)
│       │   ├── 📁 controller/     # REST and GraphQL API endpoints
│       │   ├── 📁 dto/            # DTOs for API request/response mapping
│       │   ├── 📁 exception/      # Custom exceptions and global exception handlers
│       │   ├── 📁 model/core/     # JPA entities (Airline, Flight, Booking, Passenger, etc.)
│       │   ├── 📁 repository/     # JPA Repositories (interfaces extending JpaRepository)
│       │   ├── 📁 service/        # Interfaces and implementations for business logic
│       │   ├── 📁 util/           # Helper/util classes (e.g., mappers, validators)
│       │   └── 📄 FlightBookingApplication.java  # Main Spring Boot app class
│       └── 📁 resources/
│           ├── 📄 application.yml       # Main Spring Boot config (RabbitMQ, DB, etc.)
│           ├── 📄 schema.graphqls       # GraphQL schema definition
│           ├── 📄 data.sql              # Optional: Preload DB with sample data
│           └── 📄 logback-spring.xml    # Optional: Logging configuration
│
├── 📁 docker/
│   ├── 📄 Dockerfile              # Docker build config for app container
│   ├── 📄 docker-compose.yml     # Multi-container setup (App + MySQL + RabbitMQ)
│   └── 📁 mysql-init/            # Optional: DB initialization scripts
│
├── 📄 .dockerignore              # Files ignored by Docker build
├── 📄 .gitignore                 # Files ignored by Git
├── 📄 README.md                  # Project overview and documentation
├── 📄 pom.xml                    # Maven dependencies and build plugins
└── 📄 LICENSE                    # Optional: Open-source license info

## 🌐 API Endpoints

## 📊 Monitoring

## 🧪 Testing