package com.aledsdavies.ldms;

import com.aledsdavies.ldms.repositories.PaymentScheduleRepository;
import com.aledsdavies.ldms.models.PaymentSchedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class LoanScheduleApplication {

    private final PaymentScheduleRepository paymentScheduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(LoanScheduleApplication.class, args);
    }


    /**
     * I Created this as a convenience method for preloading some data into the database for the sake of this technical
     * test. The H2 data is currently running in memory for convenience as a result will get cleared each run of the
     * application.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        log.info("Loading data to the database...");

        paymentScheduleRepository.save(PaymentSchedule.builder()
                .totalCostOfAssets(new BigDecimal(25000))
                .yearlyInterestAsDecimal(new BigDecimal("0.075"))
                .monthlyPayments(60)
                .build());

        log.info("Loading data successful Completed...");
    }
}
