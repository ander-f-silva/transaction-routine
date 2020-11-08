package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
class TransactionService implements TransactionOperation{
    @Override
    public Long authorizerTransaction(TransactionRequest transactionRequest) {
        return null;
    }
}
