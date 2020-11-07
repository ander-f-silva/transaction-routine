package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.springframework.stereotype.Service;

@Service
class AccountService implements AccountOperation {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Long create(AccountRequest accountRequest) {
        var accountEntity = accountRepository.save(new AccountEntity(accountRequest.getDocumentNumber()));
        return accountEntity.getId();
    }
}
