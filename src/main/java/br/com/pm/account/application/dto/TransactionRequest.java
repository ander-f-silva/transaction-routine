package br.com.pm.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransactionRequest {
  @JsonProperty("account_id")
  @NotNull
  @Positive
  private Long accountId;

  @JsonProperty("operation_type_id")
  @NotNull
  @Positive
  private Integer operationTypeId;

  @NotNull @Positive private Double amount;

  public TransactionRequest() {}

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
