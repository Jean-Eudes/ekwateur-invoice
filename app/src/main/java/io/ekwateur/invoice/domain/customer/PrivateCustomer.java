package io.ekwateur.invoice.domain.customer;

public record PrivateCustomer(CustomerReference reference, String firstname, String lastname, Title title) implements Customer {

}
