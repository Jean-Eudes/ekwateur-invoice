package io.ekwateur.invoice.domain;

import static java.lang.StringTemplate.STR;

import java.util.regex.Pattern;

public record CustomerReference(String reference) {

  private static final Pattern REFERENCE_PATTERN = Pattern.compile("^EKW\\d{8}$");
  public CustomerReference {
    if (!REFERENCE_PATTERN.matcher(reference).matches()) {
      throw new IllegalArgumentException(STR."reference \{reference} does not match pattern EKW\\d{8}");
    }
  }
}
