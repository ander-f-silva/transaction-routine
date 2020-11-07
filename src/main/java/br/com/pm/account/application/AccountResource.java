package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.domain.repository.AccountRepository;
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
    private final AccountRepository accountRepository;

    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody AccountRequest accountRequest) {
        var accountEntity = accountRepository.save(new AccountEntity(accountRequest.getDocumentNumber()));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
