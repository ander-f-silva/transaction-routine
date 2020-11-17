package br.com.pm.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountOutput {
  private Long id;

  @JsonProperty("document_number")
  private String documentNumber;

  public AccountOutput() {}

  public AccountOutput(Long id, String documentNumber) {
    this.id = id;
    this.documentNumber = documentNumber;
  }

  public Long getId() {
    return id;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }
}
