# hackAFlight

A scalable, microservice-ready Flight Booking Platform developed using Spring Boot. The system supports REST and GraphQL APIs, integrates RabbitMQ for asynchronous messaging, and leverages MySQL as the primary datastore. The entire application is deployed on AWS.

##  Features

-  Create, update, and manage airlines, airports, flights, and bookings
-  Manage passengers and link them to flight bookings
-  RESTful APIs for easy integration
-  GraphQL API for flexible queries
-  Asynchronous communication with RabbitMQ
-  MySQL for relational data persistence
-  Spring Boot Actuator for system health and metrics
-  AWS EC2 deployment-ready
##  Tech Stack

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

```
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/com/example/hackaflight/
│       │   ├── 📁 configuration/  # Configuration classes (e.g., RabbitMQ, CORS, Swagger, GraphQL)
│       │   ├── 📁 controller/     # REST and GraphQL API endpoints
│       │   ├── 📁 dto/            # DTOs for API request/response mapping
│       │   ├── 📁 model/core/     # JPA entities (Airline, Flight, Booking, Passenger, etc.)
│       │   ├── 📁 repository/     # JPA Repositories (interfaces extending JpaRepository)
│       │   ├── 📁 service/        # Interfaces and implementations for business logic
│       │   └── 📄 FlightBookingApplication.java  # Main Spring Boot app class
│       └── 📁 resources/
│           ├── 📁 graphql/              # Helper/util classes (e.g., mappers, validators)
│           │   └── 📄 schema.graphqls   # GraphQL schema definition
│           ├── 📁 graphql/              # Contains gql queries
│           ├── 📄 application.yml       # Main Spring Boot config (RabbitMQ, DB, etc.)
│           └── 📄 schema.graphqls       # GraphQL schema definition
│
│
├── 📄 .dockerignore              # Files ignored by Docker build
├── 📄 .gitignore                 # Files ignored by Git
├── 📄 README.md                  # Project overview and documentation
└── 📄 pom.xml                    # Maven dependencies and build plugins
```
##  API Endpoints
Below are the available REST endpoints for the Flight Booking Platform. All endpoints are accessible via `http://localhost:8080`.
- **Create Airline**  
  `GET /create-airline`  
  Example:
  ```text
  /create-airline?name=SkyAir&code=SKY&country=USA

- **Create Airport**  
  `GET /create-airport`  
  Example:
  ```text
  /create-airport?code=JFK&name=John%20F%20Kennedy%20Airport&city=New%20York&country=USA

- **Create Route**  
  `GET /create-route`  
  Example:
  ```text
  /create-route?routeId=101&originAirportId=1&destinationAirportId=2&routeCode=SKY101&description=SkyAir%20USA%20to%20UK

- **Create flight**  
  `GET /create-flight`  
  Example:
  ```text
  /create-flight?name=SkyJet%20101&departureTime=2025-06-01T08:00&arrivalTime=2025-06-01T16:00&airline=1&routeId=1

- **Create seat**  
  `GET /create-seat`  
  Example:
  ```text
  /create-seat?flightId=1&seatNumber=12A&classType=Economy&isAvailable=true&price=150.00

- **Create passenger**  
    `GET /create-passenger`  
    Example:
    ```text
    /create-passenger?firstName=John&lastName=Doe&email=hshadeesha@gmail.com&phoneNumber=1234567890&passportNumber=AB1234567

- **Add Booking**  
  `GET /add-booking`  
  Example:
    ```text
    /add-booking?passengerId=1&flightId=1&bookingDate=2025-05-10&status=CONFIRMED&seatId=1&baggageWeight=20&baggageType=Checked

- **Search Airlines by Code, Name, Country**  
  `GET /get-airlines` 
  Query Parameters
  - code : String | optional
  - Name : String | optional
  - Country : String | optional
  Example:
    ```text
    /search-bookings?passengerId=1&flightId=1&bookingDate=2025-05-10

- **Search Airports by , Country**  
  `GET /get-airports`
  Query Parameters
    - code : String | optional
    - Name : String | optional
    - City : String | optional
    - Country : String | optional
      Example:
      ```text
      /search-airports?country=UK

- **Search Passengers by First Name, Email, or Passport Number**  
  `GET /search-passengers`
  Query Parameters
    - firstName  : String | optional
    - email  : String | optional
    - passportNumber  : String | optional
      Example:
      ```text
      /search-passengers?firstName=John

- **Search bookings by passengerId, flightId or bookingDate**  
  `GET /get-bookings`
  Query Parameters
    - passengerId  : String | optional
    - flightId  : String | optional
    - bookingDate  : String | optional
      Example:
      ```text
      http://localhost:8080/get-bookings?passengerId=3&flightId=1

##  AWS Setup
- Amazon EC2: The application is hosted on an EC2 instance, providing the necessary compute power to run the backend and services.
- Amazon RDS: A managed relational database service is used to handle the application's data storage requirements, ensuring high availability, automatic backups, and easy scaling.
- RabbitMQ on Docker: RabbitMQ is running within a Docker container on the EC2 instance, providing reliable messaging for the backend services.
- Java and Docker in the Cloud: Both Java and Docker are pre-installed on the EC2 instance, simplifying the deployment and management of the application and services.
```text
ip : 
port : 
example request : 
```
##  Database
```
+---------------------+        +---------------------+         +---------------------+
|       Airline       |        |      TFlight        |         |     TBooking        |
|---------------------|        |---------------------|         |---------------------|
| airline_id (PK)     |<-------| flight_id (PK)      |<--------| booking_id (PK)     |
| name                |        | airline_id (FK)     |         | passenger_id (FK)   |
| country             |        | route_id (FK)       |         | flight_id (FK)      |
| founded_date        |        | departure_time      |         | booking_date        |
| website             |        | arrival_time        |         | status              |
+---------------------+        | duration            |         | price_paid          |
                               | price               |         | payment_method      |
                               | status              |         | seat_number         |
                               +---------------------+         +---------------------+
                                       |                              |
                                       | (FK: route_id)               | (FK: passenger_id)
                                       |                              |
                            +-----------------------+        +-------------------+
                            |        Route          |        |    TPassenger     |
                            |-----------------------|        |-------------------|
                            | route_id (PK)         |        | passenger_id (PK) |
                            | origin_airport_id (FK)|        | first_name        |
                            | destination_airport_id(FK)|    | last_name         |
                            | distance             |         | email             |
                            | travel_time          |         | phone_number      |
                            | route_code           |         | date_of_birth     |
                            +-----------------------+        | nationality       |
                                /          \                 | gender            |
                               /            \                | passport_number   |
                                                             +-------------------+
                   (FK: origin_airport_id)   (FK: destination_airport_id)
                      
                      |                          |
                      |                          |
              +-------------------+         +-------------------+
              |     Airport       |         |     Airport       |
              |-------------------|         |-------------------|
              | airport_id (PK)   |         | airport_id (PK)   |
              | airport_name      |         | airport_name      |
              | location          |         | location          |
              | country           |         | country           |
              | timezone          |         | timezone          |
              | airport_code      |         | airport_code      |
              | terminal          |         | terminal          |
              +-------------------+         +-------------------+



(1) - One-to-One or One-to-Many
(M) - Many-to-One or Many-to-Many

+ for the table border.
| for vertical connections between entities.
^, v indicate direction of relationships (arrows).
(1), (M) represent the cardinality of the relationship (One-to-One, One-to-Many).

```

Relationships:
1. tairline → tflight: One-to-Many (airline has multiple flights)
2. tairport → troute: One-to-Many (each airport can be the origin or destination of multiple routes)
3. troute → tflight: One-to-Many (each route can have multiple flights)
4. tflight → tbooking: One-to-Many (a flight can have multiple bookings)
5. tpassenger → tbooking: One-to-Many (a passenger can make multiple bookings)
6. tbooking → tbaggage: One-to-One (each booking has one baggage)
7. tbooking → tseat: One-to-One (each booking has one seat)

##  Monitoring

##  Testing