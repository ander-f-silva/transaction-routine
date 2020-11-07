package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.domain.services.AccountOperation;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
class AccountResource {
    private final AccountOperation accountOperation;

    public AccountResource(AccountOperation accountOperation) {
        this.accountOperation = accountOperation;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody AccountRequest accountRequest) {
        var id = accountOperation.create(accountRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}
