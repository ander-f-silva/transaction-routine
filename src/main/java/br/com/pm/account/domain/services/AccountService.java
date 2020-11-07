package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.application.dto.AccountResponse;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.domain.services.exception.AccountAlreadyCreatedException;
import br.com.pm.account.domain.services.exception.AccountNotFoundException;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AccountService implements AccountOperation {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Long createNewAccount(AccountRequest accountRequest) {
        var documentNumber = accountRequest.getDocumentNumber();

        if (accountRepository.existsByDocumentNumber(documentNumber)) {
            throw new AccountAlreadyCreatedException("An account already exists with the data reported");
        }

        var accountEntity = accountRepository.save(new AccountEntity(documentNumber));
        return accountEntity.getId();
    }

    @Override
    public AccountResponse getAccount(Long id) {
      return accountRepository.findById(id).map(accountEntity ->
           new AccountResponse(accountEntity.getId(), accountEntity.getDocumentNumber())
         ).orElseThrow(() -> new AccountNotFoundException("The account not found"));
    }
}
