package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.TransactionRequest;

public interface TransactionOperation {
  Long authorizerTransaction(TransactionRequest transactionRequest);
}
