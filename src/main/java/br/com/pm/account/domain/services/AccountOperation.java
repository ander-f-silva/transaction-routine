package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.application.dto.AccountResponse;

public interface AccountOperation {
    Long createNewAccount(AccountRequest accountRequest);

    AccountResponse getAccount(Long id);
}
