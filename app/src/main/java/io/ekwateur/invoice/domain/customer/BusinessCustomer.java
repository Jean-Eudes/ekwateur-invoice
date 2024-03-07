package io.ekwateur.invoice.domain.customer;

public record BusinessCustomer(CustomerReference reference, String siret, String businessName, long turnover) implements Customer {

  public boolean isBigCompany() {
    return turnover > 1_000_000;
  }

}
