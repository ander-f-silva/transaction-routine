package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountRequest;
import br.com.pm.account.application.dto.AccountResponse;
import br.com.pm.account.domain.services.AccountOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        var id = accountOperation.createNewAccount(accountRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable("id") Long id) {
        var accountResponse = accountOperation.getAccount(id);
        return ResponseEntity.ok(accountResponse);
    }
}
