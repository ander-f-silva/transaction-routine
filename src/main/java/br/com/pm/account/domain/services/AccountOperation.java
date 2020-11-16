package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountInput;
import br.com.pm.account.application.dto.AccountOutput;
import br.com.pm.account.application.dto.ErrorOutput;
import br.com.pm.account.application.dto.ResultAccountOutput;

public interface AccountOperation {
  ResultAccountOutput<Long, ErrorOutput> createNewAccount(AccountInput accountInput);

  AccountOutput getAccount(Long id);
}
