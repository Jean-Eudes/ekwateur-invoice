package io.ekwateur.invoice.domain.customer;

public sealed interface Customer permits PrivateCustomer, BusinessCustomer {

}
