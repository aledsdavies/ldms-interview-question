# LDMS Loan Schedule API

## Steps taken to implement the application

1. I first initialised the application form https://start.spring.io/ 
    - In this first commit I also initialised the database migrations into an in memory H2 database using liquibase
2. I next tackled working on the model (DAO) and repositories for accessing the database
    - The model I used Lombok to populate the constructors and implement the builder pattern on PaymentSchedule
    - Also because I am using JPA I can use Springs JpaRepository so all the crud methods for database access get implemented for me. 
    - I have also added validation to my model which I will use when I implement the service layer