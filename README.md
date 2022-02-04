# Getting Started

### Build docker image
./gradlew buildDockerImages

### Run the application
docker run -d -p 9080:9080 demo/address-service

### List address book of user u0001

curl --location --request GET 'http://localhost:9080/address-service/api/v1/u0001/addressbook' \
--header 'Content-Type: application/json'


### Swagger
http://localhost:9080/address-service/swagger-ui/
![img.png](img.png)
