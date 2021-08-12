package com.aledsdavies.ldms;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.repositories.PaymentPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class LoanScheduleApplication {

    private final PaymentPlanRepository paymentPlanRepository; // Can Be removed if the initialize function is removed

    public static void main(String[] args) {
        SpringApplication.run(LoanScheduleApplication.class, args);
    }


    /*
     * I Created this as a convenience method for preloading some data into the database for the sake of this technical
     * test. The H2 data is currently running in memory for convenience as a result will get cleared each run of the
     * application.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        log.info("Loading data to the database...");

        // Without balloon
        paymentPlanRepository.save(PaymentPlan.builder()
                .totalCostOfAssets(20000)
                .yearlyInterestAsDecimal(0.075)
                .monthlyPayments(12)
                .build());

        // With balloon
        paymentPlanRepository.save(PaymentPlan.builder()
                .totalCostOfAssets(20000)
                .yearlyInterestAsDecimal(0.075)
                .monthlyPayments(12)
                .balloonPayment(10000)
                .build());

        log.info("Loading data successful Completed...");
    }
}
