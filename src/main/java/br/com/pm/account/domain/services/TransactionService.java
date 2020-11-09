package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.TransactionRequest;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.domain.repositories.OperationTypeRepository;
import br.com.pm.account.domain.repositories.TransactionRepository;
import br.com.pm.account.domain.services.exception.TransactionNotProcessException;
import br.com.pm.account.infrastructure.jpa.entities.TransactionEntity;
import org.springframework.stereotype.Service;

@Service
class TransactionService implements TransactionOperation {
  private static final Integer OPERATION_PAYMENT_ID = 4;

  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;
  private final OperationTypeRepository operationTypeRepository;

  public TransactionService(
      AccountRepository accountRepository,
      TransactionRepository transactionRepository,
      OperationTypeRepository operationTypeRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
    this.operationTypeRepository = operationTypeRepository;
  }

  @Override
  public Long authorizerTransaction(TransactionRequest transactionRequest) {
    var accountId = transactionRequest.getAccountId();
    var operationTypeId = transactionRequest.getOperationTypeId();
    var amount = transactionRequest.getAmount();

    var account =
        accountRepository
            .findById(accountId)
            .orElseThrow(() -> new TransactionNotProcessException("The account not found"));
    var operationType =
        operationTypeRepository
            .findById(operationTypeId)
            .orElseThrow(() -> new TransactionNotProcessException("The operation not found"));

    if (!OPERATION_PAYMENT_ID.equals(operationTypeId)) {
      amount = - Math.abs(amount);
    }

    var transaction =
        transactionRepository.save(new TransactionEntity(account, operationType, amount));

    return transaction.getId();
  }
}
