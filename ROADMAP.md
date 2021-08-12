# Areas I would like to develop further with more time

1. Fully complete all the contract testing for the PaymentsPlanController
    - This would involve me agreeing the output we would expect from the controllers' endpoint in all scenarios and
      writing unit test to ensure that we get the required outputs in each scenario
2. Add more validation to the PaymentPlan model and narrow down specifically what values should and should not be
   allowed.
    - This would tie in with the first point as we would create Contract tests to validate that all Plans are valid
      before allowing them into the application
3. Clearer Error messages.
    - Generally I like error messages to be very clear on what when wrong and an action the user can perform to remedy
      the error. This could take the form of explain what value needs to be changed or added or providing a contact
      address for the user to submit a bug
4. With the way I have set up RepaymentScheduleCalculator there is scope for adding different types of repayment
   schedules then just the Annuity loan repayments