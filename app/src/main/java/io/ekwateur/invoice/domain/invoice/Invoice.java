package io.ekwateur.invoice.domain.invoice;

import io.ekwateur.invoice.domain.customer.BusinessCustomer;
import io.ekwateur.invoice.domain.customer.Customer;
import io.ekwateur.invoice.domain.customer.PrivateCustomer;
import java.math.BigDecimal;

public record Invoice(Customer customer) {

  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_FOR_PRIVATE = new BigDecimal("0.121");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_FOR_PRIVATE = new BigDecimal("0.115");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_SMALL_COMPANY = new BigDecimal("0.113");
  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_SMALL_COMPANY = new BigDecimal("0.118");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_BIG_COMPANY = new BigDecimal("0.114");
  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_BIG_COMPANY = new BigDecimal("0.111");

  public BigDecimal amount(BigDecimal electricConsumption, BigDecimal gasConsumption) {
    switch (customer) {
      case PrivateCustomer _ -> {
        BigDecimal electricityAmount = ELECTRICITY_AMOUNT_PER_MONTH_FOR_PRIVATE.multiply(electricConsumption);
        BigDecimal gazAmount = GAS_AMOUNT_PER_MONTH_FOR_PRIVATE.multiply(gasConsumption);
        return electricityAmount.add(gazAmount);
      }
      case BusinessCustomer smallBusinessCustomer when !smallBusinessCustomer.isBigCompany() -> {
        BigDecimal electricityAmount = ELECTRICITY_AMOUNT_PER_MONTH_SMALL_COMPANY.multiply(electricConsumption);
        BigDecimal gazAmount = GAS_AMOUNT_PER_MONTH_SMALL_COMPANY.multiply(gasConsumption);
        return electricityAmount.add(gazAmount);
      }
      case BusinessCustomer _ -> {
        BigDecimal electricityAmount = ELECTRICITY_AMOUNT_PER_MONTH_BIG_COMPANY.multiply(electricConsumption);
        BigDecimal gazAmount = GAS_AMOUNT_PER_MONTH_BIG_COMPANY.multiply(gasConsumption);
        return electricityAmount.add(gazAmount);
      }
    }
  }

}
