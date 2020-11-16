package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.TransactionInput;

public interface TransactionOperation {
  Long authorizerTransaction(TransactionInput transactionInput);
}
