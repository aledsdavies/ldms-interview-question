# LDMS Loan Schedule API

## Instructions for running

I have used gradle to build, run and test the application. (I have a local install but you should be able to use the
gradle wrapper on your machine if you have java installed)

- Running the project with `./gradlew bootRun`
- Running the tests with `./gradlew test`

## Viewing the App

I have left the controller pointing to the root (/) of the project this allows easy access to the data I have initialised
into the database on launch.

NOTE: The database uses an in memory database so any values you insert will be removed at launch. I did this for
convenience.

## Steps taken to implement the application

1. I first initialised the application form https://start.spring.io/
    - In this first commit I also initialised the database migrations into an in memory H2 database using liquibase
2. I next tackled working on the model (DAO) and repositories for accessing the database
    - The model I used Lombok to populate the constructors and implement the builder pattern on PaymentSchedule
    - Also because I am using JPA I can use Springs JpaRepository so all the crud methods for database access get
      implemented for me.
    - I have also added validation to my model which I will use when I implement the service layer
3. I created all the controllers and services used in the application as well as any additional models I discovered I
   needed after planning out my methods
4. I then took a TDD approach for implementing the AnnuityRepaymentScheduleCalculator and add WebMvc tests for the
   controller to show examples of how I would approach testing the controller.
5. Finally, I implemented the calculations AnnuityRepaymentScheduleCalculator.
    - I ended up getting caught here for longer than I expected because I had issues with rounding. I had originally
      tried to use BigDecimal. I have not used BigDecimal before, so I was working through an issue where I would have
      small remainders at the end of my calculation.
    - I ended up switched to using floats instead and created a rounding function to display nicely formatted values in
      the RepaymentSchedule models we return to the user.
6. I also added examples of validation annotations that could be used on the PaymentPlan model. This allows us to
   validate the input coming in form the API's clients and return a formatted error to the client using the exception
   handler method at the bottom of the controller.

## Thank you note

This ended up taking me slight longer than the 3 hours. I total the work at just over 4 hours. The majority of that came
from issues I was having with precision which meant that although the majority of the app was completed by 11pm on the
11th August it meant that I was unable to get the tests working correctly. I have then this morning (12th of August)
fixed my mistakes on a fresh pair of eyes which was around 3/4 of an hours work.

I have also taken the time to run through the application and add some documentation to explain my though processes
where I thought it would be appropriate.

Overall this was a fun little coding challenge, and hopefully this is enough to showcase how I would approach testing
and building an application like this. I appreciate the opportunity and the time you have given to me in this process.
All the best Aled. 