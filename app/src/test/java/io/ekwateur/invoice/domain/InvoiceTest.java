package io.ekwateur.invoice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class InvoiceTest {

  @Test
  void should_compute_invoice_amount_for_a_private_client_that_only_consume_electric() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    PrivateCustomer customer = new PrivateCustomer(reference, "jean", "durand", Title.Mr);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(new BigDecimal("12.5"), BigDecimal.ZERO);

    // Then
    assertThat(amount).isEqualByComparingTo("1.5125");
  }

  @Test
  void should_compute_invoice_amount_for_a_private_client_that_only_consume_gaz() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    PrivateCustomer customer = new PrivateCustomer(reference, "jean", "durand", Title.Mr);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(BigDecimal.ZERO, new BigDecimal("15"));

    // Then
    assertThat(amount).isEqualByComparingTo("1.725");
  }

  @Test
  void should_compute_invoice_amount_for_a_private_client_that_consume_gaz_and_electric() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    PrivateCustomer customer = new PrivateCustomer(reference, "jean", "durand", Title.Mr);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(new BigDecimal("12.5"), new BigDecimal("15"));

    // Then
    assertThat(amount).isEqualByComparingTo("3.2375");
  }

  @Test
  void should_compute_invoice_amount_for_a_small_company_that_consume_only_gaz() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 10_000);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(BigDecimal.ZERO, new BigDecimal("10"));

    // Then
    assertThat(amount).isEqualByComparingTo("1.13");
  }
  @Test
  void should_compute_invoice_amount_for_a_small_company_that_consume_only_electric() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 10_000);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(new BigDecimal("16"), BigDecimal.ZERO);

    // Then
    assertThat(amount).isEqualByComparingTo("1.888");
  }

  @Test
  void should_compute_invoice_amount_for_a_small_company_that_consume_gaz_and_electric() {
    // Given
    CustomerReference reference = new CustomerReference("EKW12345678");
    BusinessCustomer customer = new BusinessCustomer(reference, "jean", "durand", 10_000);
    Invoice invoice = new Invoice(customer);

    // When
    BigDecimal amount = invoice.amount(new BigDecimal("12.5"), new BigDecimal("15"));

    // Then
    assertThat(amount).isEqualByComparingTo("3.17");
  }


}