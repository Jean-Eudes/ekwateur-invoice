package io.ekwateur.invoice.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.ekwateur.invoice.domain.customer.BusinessCustomer;
import io.ekwateur.invoice.domain.customer.Customer;
import io.ekwateur.invoice.domain.customer.PrivateCustomer;
import io.ekwateur.invoice.domain.customer.Title;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {

  @Test
  void should_create_private_customer_with_args() {
    // Given
    String[] args = {"private", "EKW12345678", "MR", "jean", "durand", "12", "13"};
    CommandLineParser commandLineParser = new CommandLineParser(args);

    // When
    Customer customer = commandLineParser.parseCustomer();

    // Then
    assertThat(customer).isInstanceOf(PrivateCustomer.class);
    PrivateCustomer privateCustomer = (PrivateCustomer) customer;
    assertThat(privateCustomer.reference().reference()).isEqualTo("EKW12345678");
    assertThat(privateCustomer.firstname()).isEqualTo("jean");
    assertThat(privateCustomer.lastname()).isEqualTo("durand");
    assertThat(privateCustomer.title()).isEqualTo(Title.MR);
  }
  @Test
  void should_create_business_customer_with_args() {
    // Given
    String[] args = {"business", "EKW12345678", "802954785", "arcus", "10000", "12", "13"};
    CommandLineParser commandLineParser = new CommandLineParser(args);

    // When
    Customer customer = commandLineParser.parseCustomer();

    // Then
    assertThat(customer).isInstanceOf(BusinessCustomer.class);
    BusinessCustomer privateCustomer = (BusinessCustomer) customer;
    assertThat(privateCustomer.reference().reference()).isEqualTo("EKW12345678");
    assertThat(privateCustomer.siret()).isEqualTo("802954785");
    assertThat(privateCustomer.turnover()).isEqualTo(10_000);
    assertThat(privateCustomer.businessName()).isEqualTo("arcus");
  }

  @Test
  void should_parse_gas_and_electric_amount() {
    // Given
    String[] args = {"business", "EKW12345678", "802954785", "arcus", "10000", "12", "13"};
    CommandLineParser commandLineParser = new CommandLineParser(args);

    // When / Then
    assertThat(commandLineParser.parseGasConsumption()).isEqualTo(BigDecimal.valueOf(13));
    assertThat(commandLineParser.parseElectricConsumption()).isEqualTo(BigDecimal.valueOf(12));
  }


  @Test
  void should_fail_if_number_of_argument_is_not_correct() {
    // Given
    String[] args = {"1", "2"};

    // When /  Then
    assertThatThrownBy(() -> new CommandLineParser(args)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Le nombre d'argument doit être égale à 7");
  }

  @Test
  void should_fail_if_first_argument_is_not_private_or_business() {
    // Given
    String[] args = {"unknown", "EKW12345678", "MR", "jean", "durand", "12", "13"};
    CommandLineParser commandLineParser = new CommandLineParser(args);

    // When /  Then
    assertThatThrownBy(commandLineParser::parseCustomer).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("UNKNOWN");
  }

}