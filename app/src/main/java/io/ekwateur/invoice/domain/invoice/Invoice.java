package io.ekwateur.invoice.domain.invoice;

import io.ekwateur.invoice.domain.customer.BusinessCustomer;
import io.ekwateur.invoice.domain.customer.Customer;
import io.ekwateur.invoice.domain.customer.PrivateCustomer;
import io.ekwateur.invoice.domain.referential.Energy;
import io.ekwateur.invoice.domain.referential.PriceReferential;
import java.math.BigDecimal;
import java.util.function.Function;

public record Invoice(Customer customer, PriceReferential priceReferential) {

  public BigDecimal amount(BigDecimal electricConsumption, BigDecimal gasConsumption) {
    return switch (customer) {
      case PrivateCustomer _ -> computePrice(priceReferential::priceForPrivateCustomer, electricConsumption, gasConsumption);
      case BusinessCustomer smallBusinessCustomer when smallBusinessCustomer.isBigCompany() ->
          computePrice(priceReferential::priceForBigCompany, electricConsumption, gasConsumption);
      case BusinessCustomer _ -> computePrice(priceReferential::priceForSmallCompany, electricConsumption, gasConsumption);
    };
  }

  private BigDecimal computePrice(Function<Energy, BigDecimal> pricePerEnergy, BigDecimal electricConsumption, BigDecimal gasConsumption) {
    var electricityAmount = pricePerEnergy.apply(Energy.ELECTRICITY).multiply(electricConsumption);
    var gazAmount = pricePerEnergy.apply(Energy.GAS).multiply(gasConsumption);
    return electricityAmount.add(gazAmount);
  }

}
