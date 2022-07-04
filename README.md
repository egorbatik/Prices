# Prices - Example

## How to Start
<code>mvn spring-boot:run</code>

## How to Test
<code>mvn test</code>

## Curl for Service
<code>curl --location --request POST 'localhost:8080/price' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId":35455,
    "brandId":1,
    "dateTime":"2020-06-14T00:00:00"

}'</code>

### Other Items
* Data Tables and Seeds via Liquibase
* Test in the controller as go against seeded DB
* H2 DB
