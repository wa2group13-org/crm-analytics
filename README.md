# CRM Analytics

CRM Analytics.

## Environmental

- `MONGODB_HOST`: Default: `localhost`
- `MONGODB_USERNAME`: Default: `mongo`
- `MONGODB_PASSWORD`: Default: `mongo`
- `MONGODB_DATABASE`: Default: `mydatabase`
- `MONGODB_PORT`: Default: `27017`
- `SPRING_PROFILES_ACTIVE`: Spring comma-separated profiles. Default: `dev`, `api-docs`, `no-security`. Values:
    - `dev`: db logs and errors are returned on responses
    - `prod`: need to specify postgres connections
    - `no-security`: filers are disables
    - `api-docs`: enable `/v3/api-docs` and `/swagger-ui.html` endpoints
- `JWT_ISSUER_URI`: uri of the JWT issuer, e.g. `http://keycloak:9090/realms/app`
- `KAFKA_CONSUMER_BOOTSTRAP_SERVERS`: consumer bootstrap servers for Apache Kafka
- `OPENAPI_BASE_URL`: base url of this service that will appear in the OpenAPI documentation.
  Default `http://localhost:${PORT}`
- `PORT`: server port. Default: `8080`
