package com.aledsdavies.ldms.repositories;

import com.aledsdavies.ldms.models.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, String> {
}
