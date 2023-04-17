# Orders Service

## Setup

to start work with service it should be set the following environment variables:
- DB_USER=orders
- DB_PASSWORD=orders
- WAREHOUSE_SERVICE_BASE_URL=http://warehouse:8091


Note: please correct value of env variable if you create DB manually
    If you do it by docker-compose attached to service the db user and password will be created by script.

## Create image
to create docker image it shoudl be run:

`./gradlew jibDockerBuild`


## Open API

The Swagger UI page will then be available at <http://localhost:8092/swagger-ui.html>

The OpenAPI description will be available at the following url for json format: 
<http://localhost:8092/v3/api-docs>



