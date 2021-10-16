# Getting Started: bcf-routes
Spring Boot service, that is able to calculate any possible land route from one country to another



**Prerequisites:**

- [Java 11](https://adoptopenjdk.net/)

## Setup

To install this example, run the following commands:

```bash
git clone https://github.com/aokilipa/bcf-routes.git
cd bcf-routes
```
## Usage

#### Run with Maven

```shell
./mvnw spring-boot:run
```
OR

```
# Run tests and create a JAR
mvn package

# Run it
java -jar target/bcf-routes-0.0.1-SNAPSHOT.jar
```
Go to `http://localhost:8080`, routing endpoint `api/v1/routing/{origin}/{destination}`.

**Example:** 
```
http:localhost:8080/api/v1/routing/CZE/ITA
```
