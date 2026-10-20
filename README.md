# ProcuraX
### Enterprise Supply Chain & Supplier Risk Management System

ProcuraX is a backend application built with Java and Spring Boot to manage suppliers, products, warehouse inventory, purchase orders, deliveries, supplier performance, and procurement insights through REST APIs.

The project demonstrates enterprise backend development concepts, including JWT-based authentication, role-based authorization, database persistence, caching, audit logging, validation, centralized exception handling, API documentation, monitoring, and Docker containerization.

---

## 1. Key Features

- **Supplier Management:** Create, retrieve, update, and delete supplier records.
- **Product Management:** Maintain product information, stock levels, minimum stock thresholds, and daily demand.
- **Warehouse Management:** Manage warehouse records.
- **Inventory Management:** Track inventory quantities and reserved stock.
- **Purchase Order Management:** Manage purchase orders and their lifecycle.
- **Delivery Management:** Track deliveries and expected delivery dates.
- **Inventory Movement Tracking:** Record inventory movements such as IN, OUT, RETURN, and ADJUSTMENT.
- **Supplier Performance:** Maintain supplier performance information.
- **Procurement Insights:** Identify low-stock products, suggest reorder actions, and support supplier selection.
- **Dashboard APIs:** Provide an overview of supply chain data.
- **JWT Authentication:** Authenticate users using JSON Web Tokens.
- **Role-Based Authorization:** Restrict sensitive operations based on user roles.
- **Redis Caching:** Reduce repeated database reads for supported operations.
- **Audit Logging:** Record selected business operations for traceability.
- **Centralized Exception Handling:** Return structured API errors.
- **Bean Validation:** Validate incoming request data.
- **Swagger / OpenAPI:** Explore and test API endpoints interactively.
- **Spring Boot Actuator:** Expose configured application health and monitoring endpoints.
- **Docker:** Package and run the application in a container.

---

## 2. Technology Stack

| Technology | Purpose |
|---|---|
| Java 24 | Programming language |
| Spring Boot 3.5.5 | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database persistence |
| Hibernate | ORM |
| MySQL | Relational database |
| Spring Security | Authentication and authorization |
| JWT (JJWT) | Token-based authentication |
| Redis / Memurai | Caching |
| Lombok | Reduce boilerplate code |
| Jakarta Bean Validation | Input validation |
| Spring Boot Actuator | Health and monitoring |
| Springdoc OpenAPI | API documentation |
| Maven | Dependency and build management |
| Docker | Containerization |
| Postman | API testing |
| IntelliJ IDEA | Development environment |

---

## 3. Architecture

ProcuraX follows a layered Spring Boot architecture.

```text
                    Client Applications
                  (Postman / Swagger UI)
                            |
                            v
                     REST Controllers
                            |
                            v
                      Service Layer
                 (Business Logic & Rules)
                            |
                  +---------+---------+
                  |                   |
                  v                   v
             Repositories         Redis Cache
                  |
                  v
              Hibernate
                  |
                  v
                MySQL

    Cross-cutting Components:
    Spring Security + JWT
    DTOs + Bean Validation
    Global Exception Handling
    Audit Logging
    Actuator + Application Logging
```

### Main package responsibilities

- `config` — application configuration and OpenAPI setup.
- `controller` — REST API endpoints.
- `dto` — request and response data transfer objects.
- `exception` — custom exceptions and global exception handling.
- `model` — JPA entities.
- `procurement` — procurement insights and recommendation logic.
- `repository` — Spring Data JPA repositories.
- `security` — JWT authentication, user details, and security configuration.
- `service` — business logic, caching, and supporting services.

Package contents may evolve as the project is refactored.

---

## 4. Database

Database name: `supplyguard_db`

ProcuraX uses MySQL for persistent business data.

### Main tables

- `users`
- `suppliers`
- `products`
- `warehouses`
- `inventory`
- `purchase_orders`
- `deliveries`
- `inventory_movements`
- `supplier_products`
- `supplier_performance`

The schema supports relationships between products, suppliers, warehouses, inventory, purchase orders, and deliveries.

Create the database before starting the application:

```sql
CREATE DATABASE supplyguard_db;
```

Configure the database connection using environment-specific settings. Do not commit database passwords or other secrets to source control.

---

## 5. REST API Overview

The following endpoints represent the main API groups. Refer to Swagger UI for the exact paths, request schemas, and response formats configured in the running application.

| Module | Base path |
|---|---|
| Authentication | `/api/auth` |
| Suppliers | `/api/suppliers` |
| Products | `/api/products` |
| Warehouses | `/api/warehouses` |
| Inventory | `/api/inventory` |
| Purchase Orders | `/api/purchase-orders` |
| Deliveries | `/api/deliveries` |
| Inventory Movements | `/api/inventory-movements` |
| Supplier Performance | `/api/supplier-performance` |
| Dashboard | `/api/dashboard/summary` |

Additional procurement, audit, and monitoring endpoints depend on the application's current configuration.

### Example: Retrieve products

```http
GET /api/products
Authorization: Bearer <JWT_TOKEN>
```

### Example: Retrieve dashboard summary

```http
GET /api/dashboard/summary
Authorization: Bearer <JWT_TOKEN>
```

---

## 6. Authentication and Security

ProcuraX uses Spring Security and JWT-based authentication.

Typical authentication flow:

1. Register a user through the configured registration endpoint.
2. Log in through the authentication endpoint.
3. Obtain the JWT access token from the login response.
4. Send the token in the `Authorization` header for protected APIs.
5. Use role-based authorization to restrict privileged operations.

Example header:

```http
Authorization: Bearer <JWT_TOKEN>
```

Security considerations:

- Public registration should not allow users to assign themselves the ADMIN role.
- Restrict audit and other sensitive endpoints to authorized roles.
- Keep JWT signing secrets outside version-controlled files.
- Use HTTPS in production.
- Configure password hashing and access-control rules appropriately.
- Never commit real credentials or access tokens.

---

## 7. Caching and Audit Logging

### Redis caching

Redis caching is configured for supported application operations, including product reads.

Benefits include:
- Reduced repeated database reads.
- Improved response time for frequently requested data.
- Cache invalidation when relevant records change.

Cache behavior should be verified against the current service implementation.

### Audit logging

Audit logging records selected business operations to improve traceability.

Audit logs can help investigate changes to important business data and support operational reviews. They should be protected against unauthorized access and should not store passwords, JWT secrets, or other sensitive credentials.

---

## 8. API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

When the application is running in the Docker container configured with host port `8081`, use:

```text
http://localhost:8081/swagger-ui/index.html
```

Swagger UI allows developers to inspect API contracts and send requests. For protected endpoints, obtain a valid JWT and use the **Authorize** option.

---

## 9. Run Locally

### Prerequisites

- Java 24
- Maven
- MySQL
- Redis-compatible service, such as Memurai on Windows
- Git (optional)

### Configuration

Set the database connection and Redis settings for your local environment.

Example configuration:

```properties
spring.application.name=ProcuraX

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/supplyguard_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.data.redis.host=${REDIS_HOST:localhost}
spring.data.redis.port=${REDIS_PORT:6379}
```

These properties are an environment-based configuration example. Merge them with the existing project configuration, preserving required driver, timeout, JWT, and other application properties.

Set `DB_PASSWORD` in your local environment before starting the application. Configure any JWT secret through a secure external configuration as supported by the application.

### Build

```bash
mvn clean package -DskipTests
```

### Run

Use the packaged JAR produced by Maven:

```bash
java -jar target/procuraX-0.0.1-SNAPSHOT.jar
```

The default application port is `8080`, unless overridden by configuration.

---
## 10. Docker Deployment

ProcuraX has been packaged into a Docker image. The Products and Dashboard APIs have been tested successfully through the running container.

### Prerequisites

Before running ProcuraX in Docker, ensure that you have:

- Docker Desktop installed and running.
- MySQL running on the Windows host with the `supplyguard_db` database configured.
- Memurai or another Redis-compatible service running on the Windows host.
- Valid database credentials and a secure JWT secret configured locally.

### Build the Docker Image

Run the following command from the project root directory, where the `Dockerfile` is located:

```bash
docker build -t procurax:1.0 .
```

### Configure Environment Variables

Create a local `.env` file in the project root directory. Add your own credentials and a unique JWT secret of at least 32 bytes:

```dotenv
DB_USERNAME=root
DB_PASSWORD=your_actual_mysql_password
JWT_SECRET=replace_with_your_private_secret
```

Replace the example values with your actual local configuration. Never commit this file to GitHub or share it publicly.

### Start the Container

The following command maps host port `8081` to container port `8080` and connects the container to MySQL and Memurai running on the Windows host:

```bash
docker run -d \
  --name procurax-app \
  -p 8081:8080 \
  --add-host=host.docker.internal:host-gateway \
  --env-file .env \
  -e DB_URL=jdbc:mysql://host.docker.internal:3306/supplyguard_db \
  -e REDIS_HOST=host.docker.internal \
  procurax:1.0
```

**Note:** This configuration assumes that MySQL and Memurai are running on the host and accepting connections. The database credentials and JWT secret must match the environment variables expected by the application.

### Verify the Container

Check whether the container is running:

```bash
docker ps
```

View the container logs:

```bash
docker logs --tail 100 procurax-app
```

### Test the Container APIs

Open the following URLs in your browser or test them using Postman:

- Products API: `http://localhost:8081/api/products`
- Dashboard Summary API: `http://localhost:8081/api/dashboard/summary`

Use a valid JWT bearer token for protected endpoints.

### Stop and Remove the Container

```bash
docker stop procurax-app
docker rm procurax-app
```

### Production Considerations

For production deployment, use a managed database and Redis service, store secrets in a secure secrets manager, configure health checks and persistent storage where required, and consider Docker Compose or an orchestration platform.


## 11. Testing

Postman has been used to test the project's CRUD APIs and selected protected endpoints.

Run the automated test suite with:

```bash
mvn test
```

Package the project with tests enabled using:

```bash
mvn clean package
```

Recommended final verification:

- CRUD operations for all modules.
- Validation failures and global exception responses.
- JWT login and protected endpoint access.
- USER versus ADMIN authorization.
- Cache refresh and invalidation after writes.
- Audit log access restrictions.
- Dashboard and procurement recommendation APIs.
- Integration tests with a controlled test database.
- Docker startup, database connectivity, and API responses.

Do not report test coverage or test counts until they have been measured.

---

## 12. Future Improvements

Potential extensions include:

- Docker Compose for the application and supporting services.
- CI/CD automation.
- Database migrations with Flyway or Liquibase.
- More comprehensive automated integration tests.
- Structured audit events and stronger operational monitoring.
- Supplier risk scoring and historical trend analysis.
- Automated procurement workflows and notifications.
- Production deployment with HTTPS and managed infrastructure.

---

## 13. Project Status

ProcuraX has implemented core supply chain CRUD modules, security, caching, audit logging, procurement insights, dashboard APIs, Swagger documentation, monitoring configuration, and Docker packaging.

The Docker image `procurax:1.0` has been built, and the Products and Dashboard APIs have returned successful responses from the container during local testing.

Production readiness still depends on final security review, automated test verification, externalized secrets, and deployment configuration.

---

## License

Add a license appropriate to your intended use before publishing this repository publicly.