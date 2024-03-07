package io.ekwateur.invoice.domain;

public record BusinessCustomer(CustomerReference reference, String siret, String businessName, long turnover) {

}
