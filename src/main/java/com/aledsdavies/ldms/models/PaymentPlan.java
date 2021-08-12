package com.aledsdavies.ldms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_schedule")
public class PaymentPlan {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @DecimalMin("0.0")
    @NotNull
    private double totalCostOfAssets;

    @Builder.Default
    @DecimalMin("0.0")
    @NotNull
    private double deposit = 0.0;

    @DecimalMin("0.0")
    @NotNull
    private double yearlyInterestAsDecimal;

    @Column(name = "number_of_monthly_repayments")
    @Min(1)
    @NotNull
    private int monthlyPayments;

    @Builder.Default
    @DecimalMin("0.0")
    @NotNull
    private double balloonPayment = 0.0;
}
