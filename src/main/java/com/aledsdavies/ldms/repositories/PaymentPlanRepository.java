package com.aledsdavies.ldms.repositories;

import com.aledsdavies.ldms.models.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, String> {
}
