package io.ekwateur.invoice.domain.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BusinessCustomerTest {

  @Test
  void should_test_if_a_company_is_small() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 10_000);

    // When / Then
    Assertions.assertThat(customer.isBigCompany()).isFalse();
  }
  @Test
  void should_test_if_a_company_is_big() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 2_000_000);

    // When / Then
    Assertions.assertThat(customer.isBigCompany()).isTrue();
  }
  @Test
  void should_test_if_a_company_is_small_if_turnover_is_one_million() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 1_000_000);

    // When / Then
    Assertions.assertThat(customer.isBigCompany()).isFalse();
  }
}