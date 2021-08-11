package com.aledsdavies.ldms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentPeriod {
    private int period;
    private BigDecimal payment;
    private BigDecimal principle;
    private BigDecimal interest;
    private BigDecimal balance;
}
