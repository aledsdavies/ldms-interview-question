package com.aledsdavies.ldms.services;

import com.aledsdavies.ldms.models.PaymentPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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