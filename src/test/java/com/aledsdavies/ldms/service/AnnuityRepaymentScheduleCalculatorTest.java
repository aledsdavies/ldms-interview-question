package com.aledsdavies.ldms.service;

import com.aledsdavies.ldms.models.PaymentPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;


@ExtendWith(SpringExtension.class)
class AnnuityRepaymentScheduleCalculatorTest {

    @Autowired
    private AnnuityRepaymentScheduleCalculator repaymentCalculator;

    @Test
    void calculateRepaymentSchedule() {
        // With
        var plan = PaymentPlan.builder()
                .totalCostOfAssets(new BigDecimal(25000))
                .yearlyInterestAsDecimal(new BigDecimal("0.075"))
                .monthlyPayments(60)
                .balloonPayment(new BigDecimal(10000))
                .build();

        // Then
        var schedule = this.repaymentCalculator.calculateRepaymentSchedule(plan);

        // Expects
        var first = schedule.repaymentSchedule.get(0);

        Assertions.assertEquals(first.getPayment(), new BigDecimal("1735.15"));
        Assertions.assertEquals(first.getBalance(), new BigDecimal("18389.85"));

        var last = schedule.repaymentSchedule.get(schedule.getRepaymentSchedule().size() - 1);
        Assertions.assertEquals(first.getPayment(), new BigDecimal("1735.15"));
        Assertions.assertEquals(first.getBalance(), BigDecimal.ZERO);

    }
}