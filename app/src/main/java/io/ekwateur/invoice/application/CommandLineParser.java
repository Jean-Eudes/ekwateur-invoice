package io.ekwateur.invoice.application;

import io.ekwateur.invoice.domain.customer.BusinessCustomer;
import io.ekwateur.invoice.domain.customer.Customer;
import io.ekwateur.invoice.domain.customer.CustomerReference;
import io.ekwateur.invoice.domain.customer.PrivateCustomer;
import io.ekwateur.invoice.domain.customer.Title;
import java.math.BigDecimal;

public class CommandLineParser {

  private final String[] args;

  public CommandLineParser(String[] args) {
    if (args.length != 7) {
      throw new IllegalArgumentException("Le nombre d'argument doit être égale à 7");
    }
    this.args = args;
  }

  public Customer parseCustomer() {
    var type = args[0];
    var customerType = CustomerType.valueOf(type.toUpperCase());
    var reference = args[1];
    var customerReference = new CustomerReference(reference);

    return switch (customerType) {
      case CustomerType.BUSINESS -> {
        var siret = args[2];
        var businessName = args[3];
        var turnover = Long.parseLong(args[4]);
        yield new BusinessCustomer(customerReference, siret, businessName, turnover);
      }
      case CustomerType.PRIVATE -> {
        var title = Title.valueOf(args[2].toUpperCase());
        var firstName = args[3];
        var lastName = args[4];
        yield new PrivateCustomer(customerReference, firstName, lastName, title);
      }
    };
  }

  public BigDecimal parseElectricConsumption() {
    return new BigDecimal(args[5]);
  }
  public BigDecimal parseGasConsumption() {
    return new BigDecimal(args[6]);
  }

}
