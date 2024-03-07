package io.ekwateur.invoice.domain.referential;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PriceReferentialTest {


  @Test
  void should_find_price_by_energy() {
    // Given
    PriceReferential referential = new PriceReferential();

    // When / Then
    assertThat(referential.priceForSmallCompany(Energy.GAS)).isNotNull().isPositive();
    assertThat(referential.priceForSmallCompany(Energy.ELECTRICITY)).isNotNull().isPositive();
    assertThat(referential.priceForPrivateCustomer(Energy.GAS)).isNotNull().isPositive();
    assertThat(referential.priceForPrivateCustomer(Energy.ELECTRICITY)).isNotNull().isPositive();
    assertThat(referential.priceForBigCompany(Energy.GAS)).isNotNull().isPositive();
    assertThat(referential.priceForBigCompany(Energy.ELECTRICITY)).isNotNull().isPositive();
  }
}