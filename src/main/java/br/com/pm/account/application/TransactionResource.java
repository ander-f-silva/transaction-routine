package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.application.dto.TransactionRequest;
import br.com.pm.account.domain.services.AccountOperation;
import br.com.pm.account.domain.services.TransactionOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
class TransactionResource {
    private final TransactionOperation transactionOperation;

    public TransactionResource(TransactionOperation transactionOperation) {
        this.transactionOperation = transactionOperation;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionRequest transactionRequest) {
        var id = transactionOperation.authorizerTransaction(transactionRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

}
