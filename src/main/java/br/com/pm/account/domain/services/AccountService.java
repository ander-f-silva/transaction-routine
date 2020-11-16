package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountInput;
import br.com.pm.account.application.dto.AccountOutput;
import br.com.pm.account.application.dto.ErrorOutput;
import br.com.pm.account.application.dto.ResultAccountOutput;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.domain.services.exception.AccountNotFoundException;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.springframework.stereotype.Service;

@Service
class AccountService implements AccountOperation {
  public static final String MESSAGE_EMPTY = "";

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public AccountService() {}

  @Override
  public ResultAccountOutput<Long, ErrorOutput> createNewAccount(AccountInput accountInput) {
    var documentNumber = accountInput.getDocumentNumber();

    if (accountRepository.existsByDocumentNumber(documentNumber)) {
      return new ResultAccountOutput(
          0L, new ErrorOutput(0, "An account already exists with the data reported"));
    }

    var accountEntity = accountRepository.save(new AccountEntity(documentNumber));

    return new ResultAccountOutput(accountEntity.getId(), new ErrorOutput(MESSAGE_EMPTY));
  }

  @Override
  public AccountOutput getAccount(Long id) {
    return accountRepository
        .findById(id)
        .map(
            accountEntity ->
                new AccountOutput(accountEntity.getId(), accountEntity.getDocumentNumber()))
        .orElseThrow(() -> new AccountNotFoundException("The account not found"));
  }
}
