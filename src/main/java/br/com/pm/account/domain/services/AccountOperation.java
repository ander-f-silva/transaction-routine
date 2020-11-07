package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountRequest;

public interface AccountOperation {
    Long create(AccountRequest accountRequest);
}
