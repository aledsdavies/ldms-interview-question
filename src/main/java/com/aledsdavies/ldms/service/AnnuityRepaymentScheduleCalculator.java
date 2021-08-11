package com.aledsdavies.ldms.service;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.models.RepaymentPeriod;
import com.aledsdavies.ldms.models.RepaymentSchedule;
import com.aledsdavies.ldms.service.interfaces.RepaymentScheduleCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnnuityRepaymentScheduleCalculator implements RepaymentScheduleCalculator {

    @Override
    public RepaymentSchedule calculateRepaymentSchedule(PaymentPlan paymentPlan) {
        var repayments = new ArrayList<RepaymentPeriod>();

        return new RepaymentSchedule(paymentPlan, repayments);
    }
}
