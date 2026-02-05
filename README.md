# Microservices Architecture with Spring Boot, Kafka, and API Gateway

A production-ready microservices application built with Spring Boot, featuring JWT authentication, event-driven architecture using Apache Kafka, and a centralized API Gateway for routing and security.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Services](#services)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Configuration](#configuration)
- [Security](#security)
- [Event-Driven Architecture](#event-driven-architecture)

## 🎯 Overview

This project demonstrates a microservices architecture implementing user authentication, game management, and eligibility verification through event-driven communication. The system uses Spring Cloud Gateway for API routing, JWT for authentication, and Apache Kafka for asynchronous event processing.

## 🏗️ Architecture

```
┌─────────────┐
│   Client    │
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────────┐
│       API Gateway (Port 8083)       │
│  - JWT Token Validation             │
│  - Request Routing                  │
│  - Security Filter                  │
└──────┬──────────────────────────────┘
       │
       ├──────────────────┬──────────────────┬────────────────────┐
       ▼                  ▼                  ▼                    ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐   ┌──────────────────┐
│Auth Service │   │Game Service │   │User Service │   │Eligibility Service│
│ (Port 8080) │   │ (Port 8081) │   │ (Port 8082) │   │   (Port 8085)    │
└──────┬──────┘   └──────┬──────┘   └──────┬──────┘   └────────┬─────────┘
       │                  │                  │                   │
       │                  │                  │                   │
       └──────────────────┴──────────────────┴───────────────────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │   PostgreSQL Database  │
                    └────────────────────────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │    Apache Kafka        │
                    │  event.game-created    │
                    │  event.game-eligible   │
                    └────────────────────────┘
```

## 🔧 Services

### 1. API Gateway (`api-gateway`)
**Port:** 8083

Central entry point for all client requests, providing:
- JWT token validation
- Request routing to downstream services
- Security enforcement
- CORS configuration

**Key Components:**
- `AuthenticationFilter`: Validates JWT tokens and extracts user information
- `JwtUtils`: JWT parsing and validation utilities
- `RouterValidator`: Determines which routes require authentication
- `SecurityConfig`: Spring Security configuration

**Routes:**
- `/v1/auth/**` → Auth Service (Port 8080)
- `/v1/users/**` → Users Service (Port 8082)
- `/v1/game/**` → Game Service (Port 8081)

### 2. Auth Service API (`auth-service-api`)
**Port:** 8080

Handles user authentication and registration:
- User registration with encrypted passwords
- User login with JWT token generation
- JWT token creation and management

**Endpoints:**
- `POST /v1/auth/register` - Register new user
- `POST /v1/auth/login` - Authenticate user and receive JWT token

**Key Components:**
- `AuthService`: User registration and login logic
- `JwtService`: JWT token generation and validation
- `UserDetailsImpl`: Spring Security UserDetails implementation
- `JwtAuthFilter`: JWT authentication filter

### 3. Game Service API (`game-service-api`)
**Port:** 8081

Manages game entities with CRUD operations:
- Create, read, update, and delete games
- User-specific game management
- Event publishing to Kafka on game creation

**Endpoints:**
- `POST /v1/game/create` - Create a new game
- `GET /v1/game/get/{gameId}` - Retrieve game by ID
- `PUT /v1/game/update/{gameId}` - Update game
- `DELETE /v1/game/delete/{gameId}` - Delete game

**Key Components:**
- `GameService`: Business logic for game operations
- `GameController`: REST API endpoints
- Kafka integration via Spring Cloud Stream
- Event publishing to `event.game-created` topic

### 4. Users Service API (`users-service-api`)
**Port:** 8082

Manages user profiles and information:
- User profile retrieval
- User profile updates
- User deletion

**Endpoints:**
- `GET /v1/users/get` - Get user profile
- `PUT /v1/users/update` - Update user profile
- `DELETE /v1/users/delete` - Delete user account

**Key Components:**
- `UserService`: User management business logic
- `UserController`: REST API endpoints
- `IUserRepository`: JPA repository for user data access

### 5. Eligibility Microservice (`eligilibility-microservice`)
**Port:** 8085

Event-driven service that processes game creation events:
- Listens to `event.game-created` topic
- Validates game eligibility based on business rules
- Publishes eligibility results to `event.game-eligible` topic

**Key Components:**
- `EligibilityGameProcessor`: Processes incoming game events
- `GameEligibleService`: Business logic for eligibility validation
- Reactive programming with Project Reactor
- Spring Cloud Stream for Kafka integration

### 6. Common Library (`common-library`)

Shared library containing common entities and utilities:
- `UserModel`: Shared user entity with Spring Security integration
- Common dependencies and configurations
- Reusable components across microservices

## 💻 Technologies

### Core Framework
- **Spring Boot 3.x** - Application framework
- **Spring Cloud Gateway** - API Gateway implementation
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database access
- **Spring Cloud Stream** - Event-driven microservices

### Database & Messaging
- **PostgreSQL** - Relational database
- **Apache Kafka** - Event streaming platform
- **R2DBC** - Reactive database connectivity (Eligibility Service)

### Security & Authentication
- **JWT (JSON Web Tokens)** - Token-based authentication
- **BCrypt** - Password encryption
- **JJWT 0.11.2** - JWT library

### Development Tools
- **Maven** - Build and dependency management
- **Lombok** - Reduce boilerplate code
- **SpringDoc OpenAPI** - API documentation

### Additional Technologies
- **Project Reactor** - Reactive programming (Eligibility Service)
- **Spring WebFlux** - Reactive web framework

## 📦 Prerequisites

- **Java 21** or higher
- **Maven 3.9+**
- **PostgreSQL 12+**
- **Apache Kafka 3.x**
- **Docker** (optional, for running Kafka and PostgreSQL)

## 🚀 Getting Started

### 1. Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE "api-gateway-microservice-with-kafka";
```

### 2. Kafka Setup

Start Kafka and Zookeeper (using Docker):

```bash
# Start Zookeeper
docker run -d --name zookeeper -p 2181:2181 zookeeper:latest

# Start Kafka
docker run -d --name kafka -p 9092:9092 \
  -e KAFKA_ZOOKEEPER_CONNECT=<your-host-ip>:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://<your-host-ip>:9092 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  confluentinc/cp-kafka:latest
```

### 3. Build Common Library

Navigate to the `common-library` directory and install:

```bash
cd common-library
mvn clean install
```

### 4. Start Services

Start each service in the following order:

```bash
# 1. Auth Service
cd auth-service-api/auth-service-api
mvn spring-boot:run

# 2. Game Service
cd game-service-api/game-service-api
mvn spring-boot:run

# 3. Users Service
cd users-service-api/users-service-api
mvn spring-boot:run

# 4. Eligibility Microservice
cd eligilibility-microservice/eligilibility-microservice
mvn spring-boot:run

# 5. API Gateway (start last)
cd api-gateway/api-gateway
mvn spring-boot:run
```

### 5. Configuration

Update the database credentials in each service's `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/api-gateway-microservice-with-kafka
    username: your-username
    password: your-password
```

## 📚 API Documentation

### Authentication Flow

1. **Register a new user:**
```bash
curl -X POST http://localhost:8083/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123"
  }'
```

Response:
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9..."
}
```

2. **Login:**
```bash
curl -X POST http://localhost:8083/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securePassword123"
  }'
```

3. **Use the token for authenticated requests:**
```bash
curl -X POST http://localhost:8083/v1/game/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..." \
  -d '{
    "name": "My Awesome Game"
  }'
```

### Game Management

**Create Game:**
```bash
POST /v1/game/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Game Name"
}
```

**Get Game:**
```bash
GET /v1/game/get/{gameId}
Authorization: Bearer <token>
```

**Update Game:**
```bash
PUT /v1/game/update/{gameId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Updated Game Name"
}
```

**Delete Game:**
```bash
DELETE /v1/game/delete/{gameId}
Authorization: Bearer <token>
```

### User Management

**Get User Profile:**
```bash
GET /v1/users/get
Authorization: Bearer <token>
```

**Update User:**
```bash
PUT /v1/users/update
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "John Doe Updated",
  "email": "john.updated@example.com",
  "password": "newPassword123"
}
```

**Delete User:**
```bash
DELETE /v1/users/delete
Authorization: Bearer <token>
```

## 🔐 Security

### JWT Configuration

The system uses HS512 algorithm for JWT signing. The secret key is configured in:
- API Gateway: `JwtUtils.java`
- Auth Service: `application.yml` (`jwt.secret`)

**Important:** Change the default secret key in production!

### Security Flow

1. Client sends credentials to `/v1/auth/login`
2. Auth Service validates credentials and generates JWT token
3. Client includes token in `Authorization: Bearer <token>` header
4. API Gateway validates token using `AuthenticationFilter`
5. Valid requests are forwarded to downstream services with `X-User-Id` header
6. Services use `X-User-Id` for user-specific operations

### Protected Routes

All routes except `/v1/auth/**` require authentication. This is configured in:
- `api-gateway/SecurityConfig.java`
- `RouterValidator.java`

## 🔄 Event-Driven Architecture

### Kafka Topics

1. **event.game-created**
   - Producer: Game Service
   - Consumer: Eligibility Microservice
   - Purpose: Notify when a new game is created

2. **event.game-eligible**
   - Producer: Eligibility Microservice
   - Consumer: (To be implemented)
   - Purpose: Publish game eligibility results

### Event Flow

```
Game Service → [event.game-created] → Eligibility Service → [event.game-eligible]
```

**Game Created Event:**
```json
{
  "gameId": 1,
  "name": "Game Name",
  "userId": 123
}
```

**Game Eligible Event:**
```json
{
  "gameId": 1,
  "name": "Game Name",
  "userId": 123,
  "isEligible": true
}
```

### Kafka Configuration

Kafka settings are configured in each service's `application.yml`:

```yaml
spring:
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
          autoCreateTopics: true
          replicationFactor: 1
```

## 📊 Database Schema

### Users Table
```sql
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
```

### Games Table
```sql
CREATE TABLE games (
    game_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL
);
```

## 🧪 Testing

Run tests for each service:

```bash
cd <service-directory>
mvn test
```

## 📈 Monitoring & Health Checks

API Gateway exposes actuator endpoints:

```yaml
spring:
  endpoints:
    web:
      exposure:
        includes: "*"
```

Access health check:
```bash
curl http://localhost:8083/actuator/health
```

## 🐛 Troubleshooting

### Common Issues

1. **Port already in use:**
   - Check if services are already running
   - Change ports in `application.yml`

2. **Kafka connection failed:**
   - Ensure Kafka is running on `localhost:9092`
   - Verify Kafka broker configuration

3. **Database connection failed:**
   - Verify PostgreSQL is running
   - Check credentials in `application.yml`
   - Ensure database exists

4. **JWT token validation failed:**
   - Ensure secret keys match across services
   - Verify token format: `Bearer <token>`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 🙏 Acknowledgments

- Spring Boot team for excellent framework
- Apache Kafka for event streaming platform
- PostgreSQL community
