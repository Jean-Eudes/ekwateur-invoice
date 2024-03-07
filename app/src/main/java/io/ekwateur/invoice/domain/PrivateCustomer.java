package io.ekwateur.invoice.domain;

public record PrivateCustomer(CustomerReference reference, String firstname, String lastname, Title title) {

}
