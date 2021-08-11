package com.aledsdavies.ldms.service;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.models.RepaymentSchedule;
import com.aledsdavies.ldms.repositories.PaymentPlanRepository;
import com.aledsdavies.ldms.service.interfaces.RepaymentScheduleCalculator;
import com.aledsdavies.ldms.service.interfaces.RepaymentScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    I choose not to create testing for this class as it's mostly just using the Crud methods from the JPA repository and
    the calculation service. As a result of that there is no business logic to test in this service.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentScheduleServiceImpl implements RepaymentScheduleService {

    private final PaymentPlanRepository paymentPlanRepository;
    private final RepaymentScheduleCalculator repaymentScheduleCalculator;

    @Override
    public List<PaymentPlan> getAll() {
        return paymentPlanRepository.findAll();
    }

    @Override
    public RepaymentSchedule get(String id) throws RuntimeException {
        var plan = paymentPlanRepository.findById(id);

        if (plan.isEmpty()) {
            /*
                I am throwing an exception here as I like to create custom exception types and catch them in an
                exception handler component. See the Payment scheduler class for an example of an exception handler I
                am using to catch validation errors.
             */
            throw new RuntimeException("The id " + id + "does not exist our database");
        }

        return repaymentScheduleCalculator.calculateRepaymentSchedule(plan.get());
    }

    @Override
    public void create(PaymentPlan paymentPlan) {
        this.paymentPlanRepository.save(paymentPlan);
    }

    @Override
    public void update(PaymentPlan paymentPlan) {
        this.paymentPlanRepository.save(paymentPlan);
    }

    @Override
    public void delete(String paymentPlanId) {
        this.paymentPlanRepository.deleteById(paymentPlanId);
    }
}
