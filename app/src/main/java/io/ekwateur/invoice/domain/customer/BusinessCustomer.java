package io.ekwateur.invoice.domain.customer;

public record BusinessCustomer(CustomerReference reference, String siret, String businessName, long turnover) implements Customer {

  public BusinessCustomer {
    if (turnover < 0) {
      throw new IllegalArgumentException(STR."turnover can be positive : \{turnover} is negative.");
    }
  }

  public boolean isBigCompany() {
    return turnover > 1_000_000;
  }

}
