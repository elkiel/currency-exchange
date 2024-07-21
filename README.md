
# Currency Exchange Service

## Prerequisites

- Java 17
- Maven
- Docker

## Instructions

### Step 1: Clone the repository

```sh
git clone https://github.com/your-username/currency-exchange-service.git
cd currency-exchange/src/main/docker
```

### Step 2: Start PostgreSQL using Docker Compose

To start the PostgreSQL container, run the following command:

```sh
docker-compose -f docker/docker-compose.yml up -d
```

### Step 3: Build and run the application

```sh
mvn clean install
mvn spring-boot:run
```

### Running Tests

To run the tests, execute:

```sh
mvn test
```