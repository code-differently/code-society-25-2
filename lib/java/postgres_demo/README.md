# Postgres Demo

A Spring Boot application demonstrating PostgreSQL database connectivity with Flyway migrations, built with Gradle (Kotlin DSL).

## Features

- Spring Boot 3.2.1 application
- PostgreSQL database connection using Spring Data JDBC
- Flyway database migrations (managed by Spring Boot)
- Configuration via `application.yml` with Spring's built-in environment variable support
- Environment variable support via `.env` files (using spring-dotenv)
- Docker Compose setup for local PostgreSQL instance
- Gradle Kotlin DSL build configuration

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose (for running PostgreSQL)
- Gradle (wrapper included)

## Project Structure

```
postgres_demo/
├── app/
│   ├── build.gradle.kts          # Gradle build configuration
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/codesociety/
│           │       ├── PostgresDemoApplication.java  # Spring Boot main class
│           │       └── DemoRunner.java               # CommandLineRunner for demo
│           └── resources/
│               ├── application.yml                   # Spring Boot configuration
│               └── db/migration/
│                   ├── V1__create_users_table.sql    # Flyway migration
│                   └── V2__create_posts_table.sql    # Flyway migration
├── docker-compose.yml            # PostgreSQL Docker setup
├── .env                          # Environment variables (local)
└── .env.example                  # Environment variables template
```

## Setup

1. **Copy environment variables:**
   ```bash
   cp .env.example .env
   ```

2. **Start PostgreSQL with Docker Compose:**
   ```bash
   docker-compose up -d
   ```

3. **Verify PostgreSQL is running:**
   ```bash
   docker-compose ps
   ```

## Configuration

Spring Boot automatically loads configuration from multiple sources in this order:

### 1. application.yml (Spring Boot standard)
Located at `app/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/demo_db}
    username: ${DB_USER:demo_user}
    password: ${DB_PASSWORD:demo_password}
```

Spring Boot supports `${ENV_VAR:default_value}` syntax natively!

### 2. .env file (via spring-dotenv)
Create a `.env` file in the project root:
```
DB_URL=jdbc:postgresql://localhost:5432/demo_db
DB_USER=demo_user
DB_PASSWORD=demo_password
```

### 3. Environment variables
Export environment variables directly:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/demo_db
export DB_USER=demo_user
export DB_PASSWORD=demo_password
```

## Running the Application

### Quick start (includes Docker setup):
```bash
./start.sh
```

### Run with Gradle:
```bash
./gradlew :app:bootRun
```

### Build the application:
```bash
./gradlew :app:build
```

### Run as a JAR:
```bash
./gradlew :app:bootJar
java -jar app/build/libs/app.jar
```

## What the Application Does

1. Loads environment variables from `.env` file (via spring-dotenv)
2. Reads configuration from `application.yml` (Spring Boot handles this)
3. Runs Flyway database migrations automatically (Spring Boot integration)
4. Connects to PostgreSQL database using Spring's DataSource
5. Queries and displays users and posts from the database using JdbcTemplate

## Database Schema

The application creates two tables:

**Users table:**
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

**Posts table:**
```sql
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    published BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);
```

## Docker Commands

**Start PostgreSQL:**
```bash
docker-compose up -d
```

**Stop PostgreSQL:**
```bash
docker-compose down
```

**View PostgreSQL logs:**
```bash
docker-compose logs -f postgres
```

**Connect to PostgreSQL CLI:**
```bash
docker-compose exec postgres psql -U demo_user -d demo_db
```

**Stop and remove volumes (delete data):**
```bash
docker-compose down -v
```

## Spring Boot Features Used

- **Auto-configuration:** Spring Boot automatically configures DataSource, JdbcTemplate, and Flyway
- **Environment variable support:** Built-in `${VAR:default}` syntax in application.yml
- **Flyway integration:** Migrations run automatically on startup
- **CommandLineRunner:** DemoRunner executes after the application starts

## Dependencies

- Spring Boot Starter (3.2.1)
- Spring Boot Starter Data JDBC
- PostgreSQL JDBC Driver (42.7.1)
- Flyway Core (managed by Spring Boot)
- Flyway PostgreSQL
- Spring Dotenv (4.0.0) - for .env file support

## Troubleshooting

**Connection refused:**
- Ensure PostgreSQL is running: `docker-compose ps`
- Check the database URL in `.env` matches your setup

**Flyway migration errors:**
- Reset the database: `docker-compose down -v && docker-compose up -d`
- Check migration files in `app/src/main/resources/db/migration/`

**Build errors:**
- Clean and rebuild: `./gradlew clean :app:build`
- Ensure Java 17+ is installed: `java -version`

**Tests fail:**
- The tests require a running database or use H2 in-memory database
- To skip tests: `./gradlew :app:build -x test`

