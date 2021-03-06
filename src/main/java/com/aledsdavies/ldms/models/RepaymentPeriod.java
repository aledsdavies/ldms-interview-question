package com.aledsdavies.ldms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentPeriod {
    private int period;
    private double payment;
    private double principle;
    private double interest;
    private double balance;
}
