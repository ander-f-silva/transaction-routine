package br.com.pm.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

public class AccountInput {
  @JsonProperty("document_number")
  @CPF
  private String documentNumber;

  public AccountInput() {}

  public AccountInput(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }
}
