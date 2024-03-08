package io.ekwateur.invoice.application;

import io.ekwateur.invoice.domain.customer.Customer;
import io.ekwateur.invoice.domain.invoice.Invoice;
import io.ekwateur.invoice.domain.referential.PriceReferential;
import java.text.DecimalFormat;

public class InvoiceApplication {

  public void generate(String[] args) {
    var df = new DecimalFormat("#,###.00");
    var priceReferential = new PriceReferential();

    CommandLineParser commandLineParser = null;
    Customer customer = null;
    try {
      commandLineParser = new CommandLineParser(args);
      customer = commandLineParser.parseCustomer();
    } catch (IllegalArgumentException e) {
      System.out.printf("vos argument sont invalides pour la raison suivante : %s\n", e.getMessage());
      System.out.print("Voici des exemples d'aruments valide :\n");
      System.out.print("   business EKW12345678 802954785 arcus 10000000 12.4 21334.90.\n");
      System.out.print("   private EKW12345678 MR jean durand 12.4 2134.90.\n");
      System.exit(1);
    }

    var invoice = new Invoice(customer, priceReferential);

    var amount = invoice.amount(commandLineParser.parseElectricConsumption(), commandLineParser.parseGasConsumption());

    System.out.printf("Le montant de la facture pour ce client est : %s €.\n", df.format(amount));
  }

}
