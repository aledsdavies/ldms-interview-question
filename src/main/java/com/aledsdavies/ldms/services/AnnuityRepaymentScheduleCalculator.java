package com.aledsdavies.ldms.services;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.models.RepaymentPeriod;
import com.aledsdavies.ldms.models.RepaymentSchedule;
import com.aledsdavies.ldms.services.interfaces.RepaymentScheduleCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnuityRepaymentScheduleCalculator implements RepaymentScheduleCalculator {

    @Override
    public RepaymentSchedule calculateRepaymentSchedule(PaymentPlan paymentPlan) {
        var monthlyInterest = paymentPlan.getYearlyInterestAsDecimal() / 12;

        double monthlyRepaymentRate = calculateRepaymentRate(paymentPlan, monthlyInterest);

        var repayments = buildRepaymentSchedule(paymentPlan, monthlyRepaymentRate, monthlyInterest);

        return new RepaymentSchedule(paymentPlan, repayments);
    }

    private List<RepaymentPeriod> buildRepaymentSchedule(PaymentPlan paymentPlan, double monthlyRepaymentRate, double monthlyInterest) {
        var repayments = new ArrayList<RepaymentPeriod>();
        var remainingBalance = paymentPlan.getTotalCostOfAssets();
        for (int i = 1; i <= paymentPlan.getMonthlyPayments(); i++) {
            var interest = remainingBalance * monthlyInterest;
            var principal = monthlyRepaymentRate - interest;
            remainingBalance = remainingBalance - principal;

            var plan = new RepaymentPeriod(
                    i,
                    round(monthlyRepaymentRate, 2),
                    round(principal, 2),
                    round(interest, 2),
                    round(remainingBalance, 2)
            );

            repayments.add(plan);
        }
        return repayments;
    }

    private double calculateRepaymentRate(PaymentPlan paymentPlan, double monthlyInterest) {
        if (paymentPlan.getBalloonPayment() == 0.0f) {
            return calculateMonthlyRepaymentWithoutBalloon(paymentPlan, monthlyInterest);
        } else {
            return calculateMonthlyRepaymentWithBalloon(paymentPlan, monthlyInterest);
        }
    }

    private double calculateMonthlyRepaymentWithBalloon(PaymentPlan paymentPlan, double monthlyInterest) {
        var rhs = (paymentPlan.getTotalCostOfAssets() - paymentPlan.getDeposit()) - (paymentPlan.getBalloonPayment() / Math.pow(1f + monthlyInterest, paymentPlan.getMonthlyPayments()));

        var lhs = monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -paymentPlan.getMonthlyPayments()));

        return rhs * lhs;
    }


    private double calculateMonthlyRepaymentWithoutBalloon(PaymentPlan paymentPlan, double monthlyInterest) {
        double rhs = monthlyInterest * Math.pow(1 + monthlyInterest, paymentPlan.getMonthlyPayments());
        double lhs = Math.pow(1 + monthlyInterest, paymentPlan.getMonthlyPayments()) - 1;

        return (paymentPlan.getTotalCostOfAssets() - paymentPlan.getDeposit()) * (rhs / lhs);
    }

    /*
        Convenience method to help with rounding
     */
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
