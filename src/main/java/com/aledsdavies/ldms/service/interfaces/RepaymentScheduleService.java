package com.aledsdavies.ldms.service.interfaces;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.models.RepaymentSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepaymentScheduleService {
    List<PaymentPlan> getAll();
    RepaymentSchedule get(String id);
    void create(PaymentPlan paymentPlan);
    void update(PaymentPlan paymentPlan);
    void delete(String paymentPlanId);
}

