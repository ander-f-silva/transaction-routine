package br.com.pm.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequest {
  @JsonProperty("account_id")
   private Long accountId;

  @JsonProperty("operation_type_id")
  private Integer operationTypeId;

  private Double amount;

  public TransactionRequest() {
  }

  public TransactionRequest(Long accountId, Integer operationTypeId, Double amount) {
    this.accountId = accountId;
    this.operationTypeId = operationTypeId;
    this.amount = amount;
  }

  public Long getAccountId() {
    return accountId;
  }

  public Integer getOperationTypeId() {
    return operationTypeId;
  }

  public Double getAmount() {
    return amount;
  }
}
