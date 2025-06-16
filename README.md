
# spring-boot-testing-ground
## Spring Boot Testing Ground
This is a simple Spring Boot application that serves as a testing ground for various Spring Boot features and functionalities. The application includes a RESTful API for managing items, with basic CRUD operations and exception handling.
It also demonstrates the use of H2 in-memory database for testing purposes, along with JUnit 5 and Mockito for unit testing.

## Features
- RESTful API for managing items
- CRUD operations (Create, Read, Update, Delete)
- Exception handling
- H2 in-memory database for testing
- Unit testing with JUnit 5 and Mockito
- Integration testing with Spring Boot Test
- API documentation with Swagger
- Spring Boot DevTools for hot reloading
- Spring Boot Actuator for monitoring
- Spring Boot Security for basic authentication
- Spring Boot AOP for logging
- Spring Boot Cache for caching
- Spring Boot Mail for sending emails
- Spring Boot Redis for caching
- Spring Boot MongoDB for NoSQL database
- Spring Boot Elasticsearch for search
- Spring Boot Cassandra for NoSQL database
- Spring Boot Couchbase for NoSQL database
- Spring Boot Neo4j for graph database
- Spring Boot Solr for search
- Spring Boot JDBC for relational database
- Spring Boot R2DBC for reactive programming
- Spring Boot JOOQ for SQL query building
- Spring Boot GraphQL for API
- Spring Boot REST for API
- Spring Boot WebFlux for reactive programming
- Spring Boot Reactive for reactive programming
- Spring Boot ReactiveMongoDB for reactive programming
- Spring Boot ReactiveCassandra for reactive programming
- Spring Boot ReactiveCouchbase for reactive programming
- Spring Boot ReactiveNeo4j for reactive programming
- Spring Boot ReactiveSolr for reactive programming
- Spring Boot ReactiveJOOQ for reactive programming
- Spring Boot ReactiveGraphQL for reactive programming
- Spring Boot ReactiveREST for reactive programming
- Spring Boot ReactiveWebFlux for reactive programming
- Spring Boot ReactiveReactive for reactive programming
- Spring Boot ReactiveReactiveMongoDB for reactive programming
- Spring Boot ReactiveReactiveCassandra for reactive programming
- Spring Boot ReactiveReactiveCouchbase for reactive programming


## Project Structure
```
spring-boot-testing-ground

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── springboottest
│   │   │               ├── SpringBootTestingGroundApplication.java
│   │   │               ├── controller
│   │   │               │   └── ItemController.java
│   │   │               ├── dto
│   │   │               │   └── ItemDto.java
│   │   │               ├── exception
│   │   │               │   ├── ItemNotFoundException.java
│   │   │               │   └── RestResponseEntityExceptionHandler.java
│   │   │               ├── model
│   │   │               │   └── Item.java
│   │   │               ├── repository
│   │   │               │   └── ItemRepository.java
│   │   │               ├── service
│   │   │               │   ├── ItemService.java
│   │   │               │   └── ItemServiceImpl.java
│   │   │               └── util
│   │   │                   └── ItemMapper.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │           └── index.html
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── springboottest
│       │               ├── SpringBootTestingGroundApplicationTests.java
│       │               ├── controller
│       │               │   └── ItemControllerTest.java
│       │               ├── exception
│       │               │   └── RestResponseEntityExceptionHandlerTest.java
│       │               ├── model
│       │               │   └── ItemTest.java
│       │               ├── repository
│       │               │   └── ItemRepositoryTest.java
│       │               └── service
│       │                   └── ItemServiceTest.java
│       └── resources
│           └── application-test.properties
└── pom.xml
```
## How to Run the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/amigaura/spring-boot-testing-ground.git
   
    cd spring-boot-testing-ground
    ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
   
3. Run the application:
   ```bash
    mvn spring-boot:run
    ```
   
4. Access the application:
   Open your web browser and navigate to `http://localhost:8080` to see the application in action.

5. Test the API:
   You can use tools like Postman or curl to test the API endpoints. For example, to create a new item, you can use the following curl command:
   ```bash
   curl --location 'http://localhost:8080/api/items' \
   --header 'Content-Type: application/json' \
   --data '{
   "id": 1,
   "name": "Mouse",
   "description": "Black Mouse",
   "price": 240.0
   }'
   ```
```bash
   curl --location 'http://localhost:8080/api/items/async/process-items' \
--header 'Content-Type: application/json' \
--data '[{
"name": "Mouse",
"description": "Black Mouse",
"price": 240.0
},
{
"name": "Data Cable",
"description": "2 mere Long Data Cable",
"price": 100.0
},
{
"name": "Computer",
"description": "HP Desktop Computer",
"price": 12330.0
},
{
"name": "Keyboard",
"description": "Wired Keyboard",
"price": 244.0
},
{
"name": "Speaker",
"description": "JBL Speaker",
"price": 432.0
},
{
"name": "UPS",
"description": "12V - 30 mins power back up",
"price": 4244.0
}]'
   ```   

```bash
curl --location 'http://localhost:8080/api/items'

curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "admin",
  "password": "amit"
}'

```

6. Run Tests:
   You can run the tests using Maven:
   ```bash
   mvn test
   ```
7. Access the H2 Console:
   Open your web browser and navigate to `http://localhost:8080/h2-console` to access the H2 database console. Use the following credentials:
   - **JDBC URL**: `jdbc:h2:mem:testdb` / jdbc:h2:file:../../h2/data/testdb
   - **User Name**: `sa`
   - **Password**: (leave it blank)
   - **Driver Class**: `org.h2.Driver`
   - **Click on "Connect"** to access the H2 database.
   - You can view the data in the `items` table by executing the following SQL query:
   ```sql
    SELECT * FROM items;
    ```
## Technologies Used
- Java 17
- Spring Boot 2.7.0
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito
- Spring Web
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Boot Starter Web
- Spring Boot Starter Validation
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Actuator
- Spring Boot Starter Security
- Spring Boot Starter AOP
- Spring Boot Starter Cache
- Spring Boot Starter Mail
- Spring Boot Starter Data Redis
- Spring Boot Starter Data MongoDB
- Spring Boot Starter Data Elasticsearch
- Spring Boot Starter Data Cassandra
- Spring Boot Starter Data Couchbase
- Spring Boot Starter Data Neo4j
- Spring Boot Starter Data Solr
- Spring Boot Starter Data JDBC
- Spring Boot Starter Data R2DBC
- Spring Boot Starter Data JOOQ
- Spring Boot Starter Data GraphQL
- Spring Boot Starter Data REST
- Spring Boot Starter Data WebFlux
- Spring Boot Starter Data Reactive
- Spring Boot Starter Data Reactive MongoDB
- Spring Boot Starter Data Reactive Cassandra
- Spring Boot Starter Data Reactive Couchbase
- Spring Boot Starter Data Reactive Neo4j
- Spring Boot Starter Data Reactive Solr
- Spring Boot Starter Data Reactive JDBC
- Spring Boot Starter Data Reactive R2DBC
- Spring Boot Starter Data Reactive JOOQ
- Spring Boot Starter Data Reactive GraphQL
- Spring Boot Starter Data Reactive REST
- Spring Boot Starter Data Reactive WebFlux
- Spring Boot Starter Data Reactive Reactive
- Spring Boot Starter Data Reactive ReactiveMongoDB
- Spring Boot Starter Data Reactive ReactiveCassandra
- Spring Boot Starter Data Reactive ReactiveCouchbase
- Spring Boot Starter Data Reactive ReactiveNeo4j
- Spring Boot Starter Data Reactive ReactiveSolr
- Spring Boot Starter Data Reactive ReactiveJOOQ
- Spring Boot Starter Data Reactive ReactiveGraphQL
- Spring Boot Starter Data Reactive ReactiveREST
- Spring Boot Starter Data Reactive ReactiveWebFlux
- Spring Boot Starter Data Reactive ReactiveReactive
- Spring Boot Starter Data Reactive ReactiveReactiveMongoDB
- Spring Boot Starter Data Reactive ReactiveReactiveCassandra
- Spring Boot Starter Data Reactive ReactiveReactiveCouchbase
- Spring Boot Starter Data Reactive ReactiveReactiveNeo4j
- Spring Boot Starter Data Reactive ReactiveReactiveSolr
- Spring Boot Starter Data Reactive ReactiveReactiveJOOQ
- Spring Boot Starter Data Reactive ReactiveReactiveGraphQL