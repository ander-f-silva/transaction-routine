package br.com.pm.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

public class AccountRequest {
  @JsonProperty("document_number")
  @CPF
  private String documentNumber;

  public AccountRequest() {}

  public AccountRequest(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }
}
