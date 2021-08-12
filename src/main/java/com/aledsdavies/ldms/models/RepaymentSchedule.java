package com.aledsdavies.ldms.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    I decided to keep the RepaymentSchedule separate from my PaymentPlan object. I prefer to keep objects knowing what
    they need to know and the only time I decided to return the Repayment schedule was in the get(id) rest endpoint.
 */

@Getter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentSchedule extends PaymentPlan {
    public List<RepaymentPeriod> repaymentSchedule;

    public RepaymentSchedule(PaymentPlan paymentPlan, List<RepaymentPeriod> repaymentSchedule) {
        super(
                paymentPlan.getId(),
                paymentPlan.getTotalCostOfAssets(),
                paymentPlan.getDeposit(),
                paymentPlan.getYearlyInterestAsDecimal(),
                paymentPlan.getMonthlyPayments(),
                paymentPlan.getBalloonPayment());
        this.repaymentSchedule = repaymentSchedule;
    }
}

