package com.aledsdavies.ldms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_schedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty(message = "Total Costs is a required")
    private BigDecimal totalCostOfAssets;

    @Builder.Default
    private BigDecimal deposit = BigDecimal.ZERO;

    @NotEmpty(message = "Yearly Interest is required")
    private BigDecimal yearlyInterestAsDecimal;

    @NotEmpty(message = "Monthly payments is required")
    @Column(name = "number_of_monthly_repayments")
    private int monthlyPayments;

    @Builder.Default
    private BigDecimal balloonPayment = BigDecimal.ZERO;
}
