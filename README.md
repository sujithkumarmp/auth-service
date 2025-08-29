# Auth Service

A Spring Boot-based authentication service that provides user registration and authentication capabilities as part of a microservices architecture.

## Overview

This authentication service is designed to handle user credentials and provide signup/signin functionality. It integrates with Spring Cloud ecosystem for configuration management, service discovery, and distributed tracing.

## Technology Stack

- **Java**: 11
- **Spring Boot**: 2.7.14
- **Spring Cloud**: 2021.0.8
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Service Discovery**: Netflix Eureka
- **Configuration**: Spring Cloud Config
- **Monitoring**: Micrometer (Prometheus), Zipkin
- **Development**: Lombok

## Features

- User registration (signup)
- User authentication (signin)
- Integration with Spring Cloud Config Server
- Service discovery with Eureka
- Health monitoring with Spring Boot Actuator
- Distributed tracing with Zipkin
- Metrics collection with Prometheus

## Prerequisites

Before running this service, ensure you have:

- Java 11 or higher
- Maven 3.6+ (or use the provided Maven wrapper)
- PostgreSQL database
- Spring Cloud Config Server (running on port 8071)
- Netflix Eureka Server (for service discovery)

## Configuration

The service uses the following default configuration:

- **Server Port**: 8090
- **Application Name**: auth-service
- **Active Profile**: prod
- **Config Server**: http://localhost:8071

### Database Configuration

Configure your PostgreSQL database connection in the external config server or modify the `application.properties` file with:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/auth_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## API Endpoints

### Base URL
```
http://localhost:8090/api/v1
```

### Endpoints

#### User Signup
```http
POST /api/v1/signup
Content-Type: application/json

{
    "username": "user123",
    "password": "password123"
}
```

**Response:**
```http
HTTP/1.1 200 OK
Content-Type: text/plain

New Signup Successful
```

#### User Signin
```http
POST /api/v1/signin
Content-Type: application/json

{
    "username": "user123",
    "password": "password123"
}
```

**Response:**
```http
HTTP/1.1 200 OK
Content-Type: text/plain

Sign in Successful
```

### Health Check
```http
GET /actuator/health
```

### Metrics (Prometheus format)
```http
GET /actuator/prometheus
```

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/sujithkumarmp/auth-service.git
cd auth-service
```

### 2. Setup External Dependencies

Before starting the auth service, ensure the following services are running:

#### Config Server
Start a Spring Cloud Config Server on port 8071, or update the configuration to point to your config server.

#### Database
Create a PostgreSQL database and configure the connection details.

#### Eureka Server (Optional)
If using service discovery, ensure Eureka server is running.

### 3. Build the Project
```bash
./mvnw clean compile
```

### 4. Run Tests
```bash
./mvnw test
```

### 5. Run the Application
```bash
./mvnw spring-boot:run
```

Or build and run the JAR:
```bash
./mvnw clean package
java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```

## Development

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/auth/authservice/
│   │       ├── AuthServiceApplication.java    # Main application class
│   │       ├── MainRestController.java        # REST endpoints
│   │       ├── Credential.java                # User credential entity
│   │       └── CredentialRepository.java      # JPA repository
│   └── resources/
│       └── application.properties             # Application configuration
└── test/
    └── java/
        └── com/auth/authservice/
            └── AuthServiceApplicationTests.java
```

### Database Schema

The service creates a `credentials` table with the following structure:

| Column   | Type         | Constraints           |
|----------|-------------|-----------------------|
| username | VARCHAR(20)  | Primary Key, NOT NULL |
| password | VARCHAR(20)  | NOT NULL              |

### Adding New Features

1. **New Endpoints**: Add methods to `MainRestController.java`
2. **Data Models**: Create new entity classes in the same package
3. **Repository Layer**: Extend `JpaRepository` for new entities
4. **Business Logic**: Add service classes between controllers and repositories

## Configuration Profiles

The application supports different profiles:

- **prod**: Production profile (default)
- **dev**: Development profile
- **test**: Testing profile

Switch profiles using:
```bash
java -jar auth-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

## Monitoring and Observability

### Health Checks
- Endpoint: `/actuator/health`
- Shows application and dependency health status

### Metrics
- Endpoint: `/actuator/prometheus`
- Provides application metrics in Prometheus format

### Distributed Tracing
- Integrated with Zipkin for request tracing
- Trace data sent to configured Zipkin server

## Security Considerations

⚠️ **Note**: This is a basic implementation. For production use, consider:

- Implementing proper password hashing (BCrypt)
- Adding input validation
- Implementing JWT tokens for stateless authentication
- Adding rate limiting
- Using HTTPS
- Implementing proper error handling
- Adding authentication/authorization mechanisms

## Docker Support

To containerize the application:

1. Create a `Dockerfile`:
```dockerfile
FROM openjdk:11-jre-slim
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

2. Build and run:
```bash
./mvnw clean package
docker build -t auth-service .
docker run -p 8090:8090 auth-service
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support and questions, please open an issue in the GitHub repository.