package io.ekwateur.invoice.domain.referential;

import java.math.BigDecimal;

public record PriceReferential() {

  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_FOR_PRIVATE = new BigDecimal("0.121");
  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_SMALL_COMPANY = new BigDecimal("0.118");
  private static final BigDecimal ELECTRICITY_AMOUNT_PER_MONTH_BIG_COMPANY = new BigDecimal("0.114");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_FOR_PRIVATE = new BigDecimal("0.115");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_SMALL_COMPANY = new BigDecimal("0.113");
  private static final BigDecimal GAS_AMOUNT_PER_MONTH_BIG_COMPANY = new BigDecimal("0.111");

  public BigDecimal priceForPrivateCustomer(Energy energy) {
    return switch (energy) {
      case ELECTRICITY -> ELECTRICITY_AMOUNT_PER_MONTH_FOR_PRIVATE;
      case GAS -> GAS_AMOUNT_PER_MONTH_FOR_PRIVATE;
    };
  }

  public BigDecimal priceForSmallCompany(Energy energy) {
    return switch (energy) {
      case ELECTRICITY -> ELECTRICITY_AMOUNT_PER_MONTH_SMALL_COMPANY;
      case GAS -> GAS_AMOUNT_PER_MONTH_SMALL_COMPANY;
    };
  }

  public BigDecimal priceForBigCompany(Energy energy) {
    return switch (energy) {
      case ELECTRICITY -> ELECTRICITY_AMOUNT_PER_MONTH_BIG_COMPANY;
      case GAS -> GAS_AMOUNT_PER_MONTH_BIG_COMPANY;
    };
  }

}
