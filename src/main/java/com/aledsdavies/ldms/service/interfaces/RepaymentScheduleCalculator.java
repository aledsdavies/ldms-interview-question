package com.aledsdavies.ldms.service.interfaces;

import com.aledsdavies.ldms.models.RepaymentSchedule;
import com.aledsdavies.ldms.models.PaymentPlan;
import org.springframework.stereotype.Service;


/*
    I created a repayment schedule calculator for extensibility purposes. In the future we may want to calculate other
    types of loan repayment schedules than annuity. We could then inject all the implementations into the
    PaymentScheduleService using a map. The map would then allow us to discriminate which service we want to use, using
    the beans names.

    I also decided to calculate these values in code as apposed to storing them in the database as with the PaymentPlan
    object has all the information required to calculate different payment plans and storing the RepaymentPlan information
    seemed redundant.
 */

@Service
public interface RepaymentScheduleCalculator {
    RepaymentSchedule calculateRepaymentSchedule(PaymentPlan repaymentDetails);
}
