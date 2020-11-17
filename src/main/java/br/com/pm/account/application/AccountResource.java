package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountInput;
import br.com.pm.account.application.dto.AccountOutput;
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
  public ResponseEntity<?> create(@Valid @RequestBody AccountInput accountInput) {
    var informationCreationAccount = accountOperation.createNewAccount(accountInput);

    long invalidAccountId = 0L;

    if (informationCreationAccount.getInput() == invalidAccountId) {
      return ResponseEntity.unprocessableEntity().body(informationCreationAccount.getError());
    } else {
      var accountId = informationCreationAccount.getInput();

      URI location =
              ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountId).toUri();
      return ResponseEntity.created(location).build();
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AccountOutput> findById(@PathVariable("id") Long id) {
    var accountResponse = accountOperation.getAccount(id);
    return ResponseEntity.ok(accountResponse);
  }
}
