# sample-crm REST API based CRM system

This is Customer Profile Microservice, which is providing REST endpoints for performing CRUD related operations.

## The operations allowed are
 * Get all  
 * Get a specific customer profile with id  
 * update customer profile  
 * delete customer profile  

If the requested resource in GET, PUT and DELETE operation is not present then the API will return ERROR with HTTP 404 status.


## Steps to setup project on local

* Clone the repository locally  
* go into the directory : cd sample-crm  
* execute command : mvn clean install  
* execute command : mvn spring-boot:run  

Once above steps are done the microservice will be up and running on port 8089. The port can be changed by altering the 'server.port' property in application.properties file inside resource folder of src directory.

## Database

As a initial stage H2 database is used for storing customer profile.

## Test cases  

There two type of test cases added 
* Service level JUnit test cases using mockito
* Controller level BDD test cases to cover happy path business scenarios

## Documentation

The swagger documentation is available at the URL

localhost:8089/swagger-ui.html




## Work to be done

## Authentication and Authorization

The endpoints can be devided into two categories  
* publicly accessible endpoints
* Endpoints that requires authentication or authorization

Security can be implemented using Spring Zull and JWT token based authentication mecanism.  

There will be sign up module for adding authenticated users for performing non public actions.

User will pass username & password over secure https based connection, once autheniticated JWT token is returned to the user which will be used for subsequent request.  



