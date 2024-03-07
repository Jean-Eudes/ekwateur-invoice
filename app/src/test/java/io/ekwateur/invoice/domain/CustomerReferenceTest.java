package io.ekwateur.invoice.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CustomerReferenceTest {

  @Test
  void should_test_if_a_customer_reference_is_valid() {
    // Given / When / Then
    assertThatCode(() -> new CustomerReference("EKW12345678")).doesNotThrowAnyException();
  }

  @Test
  void should_test_customer_reference_with_invalid_prefix() {
    // Given / When / Then
    assertThatThrownBy(() -> new CustomerReference("EKX12345678")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void should_test_customer_reference_with_invalid_numeric_character() {
    // Given / When / Then
    assertThatThrownBy(() -> new CustomerReference("EKW1234567a")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void should_test_customer_reference_with_invalid_numeric_size() {
    // Given / When / Then
    assertThatThrownBy(() -> new CustomerReference("EKW123456789")).isInstanceOf(IllegalArgumentException.class);
  }
}