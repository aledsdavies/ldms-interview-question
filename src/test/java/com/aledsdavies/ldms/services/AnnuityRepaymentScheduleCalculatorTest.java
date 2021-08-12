package com.aledsdavies.ldms.services;

import com.aledsdavies.ldms.models.PaymentPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/*
    The bulk of the business logic is in the AnnuityRepaymentScheduleCalculator as a result I took a TDD approach to testing
    this service. After writing the tests I then implemented the functionality in the service and ensured that it passed all
    my tests.

    The rest of the application is hooking different services together using spring beans and autowiring and does not
    contain any business logic. The way I like to test these other services which don't contain business logic is to use
    End-to-End testing on the whole service which I have omitted from this project. However, you could also use integration
    testing for some finer grained testing on services which require other integrations.
 */

@SpringBootTest
class AnnuityRepaymentScheduleCalculatorTest {

    @Autowired
    private AnnuityRepaymentScheduleCalculator repaymentCalculator;

    @Test
    void calculateRepaymentScheduleWithoutBalloon() {
        // With
        var plan = PaymentPlan.builder()
                .totalCostOfAssets(20000)
                .yearlyInterestAsDecimal(0.075)
                .monthlyPayments(12)
                .balloonPayment(0)
                .build();

        // Then
        var schedule = this.repaymentCalculator.calculateRepaymentSchedule(plan);

        // Expects
        var first = schedule.repaymentSchedule.get(0);

        Assertions.assertEquals(1735.15, first.getPayment());
        Assertions.assertEquals(18389.85, first.getBalance());

        var last = schedule.repaymentSchedule.get(schedule.getRepaymentSchedule().size() - 1);
        Assertions.assertEquals(1735.15, last.getPayment());
        Assertions.assertEquals(0, last.getBalance());
    }


    @Test
    void calculateRepaymentScheduleWithBalloon() {
        // With
        var plan = PaymentPlan.builder()
                .totalCostOfAssets(20000)
                .yearlyInterestAsDecimal(0.075)
                .monthlyPayments(12)
                .balloonPayment(10000)
                .build();

        // Then
        var schedule = this.repaymentCalculator.calculateRepaymentSchedule(plan);

        // Expects
        var first = schedule.repaymentSchedule.get(0);

        Assertions.assertEquals(930.07, first.getPayment());
        Assertions.assertEquals(19194.93, first.getBalance());

        var last = schedule.repaymentSchedule.get(schedule.getRepaymentSchedule().size() - 1);
        Assertions.assertEquals(930.07, last.getPayment());
        Assertions.assertEquals(10000, last.getBalance());
    }
}