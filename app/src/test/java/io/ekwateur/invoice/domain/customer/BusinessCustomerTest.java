package io.ekwateur.invoice.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BusinessCustomerTest {

  @Test
  void should_test_if_a_company_is_small() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "802954785", "arcus", 10_000);

    // When / Then
    assertThat(customer.isBigCompany()).isFalse();
  }
  @Test
  void should_test_if_a_company_is_big() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "802954785", "arcus", 2_000_000);

    // When / Then
    assertThat(customer.isBigCompany()).isTrue();
  }
  @Test
  void should_test_if_a_company_is_small_if_turnover_is_one_million() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "802954785", "arcus", 1_000_000);

    // When / Then
    assertThat(customer.isBigCompany()).isFalse();
  }

  @Test
  void should_fail_if_turnover_is_negative() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");

    // When / Then
    assertThatThrownBy(() -> new BusinessCustomer(reference, "802954785", "arcus", -1)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("turnover can be positive : -1 is negative.");
  }

}