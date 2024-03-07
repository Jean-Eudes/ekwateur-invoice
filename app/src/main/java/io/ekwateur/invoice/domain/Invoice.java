package io.ekwateur.invoice.domain;

import java.math.BigDecimal;

public record Invoice(PrivateCustomer customer) {

  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH = new BigDecimal("0.121");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH = new BigDecimal("0.115");

  public BigDecimal amount(BigDecimal electricConsumption, BigDecimal gasConsumption) {
    BigDecimal electricityAmount = ELECTRICITY_AMOUNT_PER_MONTH.multiply(electricConsumption);
    BigDecimal gazAmount = GAS_AMOUNT_PER_MONTH.multiply(gasConsumption);
    return electricityAmount.add(gazAmount);
  }

}
