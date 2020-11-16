package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountInput;
import br.com.pm.account.application.dto.AccountOutput;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountsResourceTest {
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private AccountRepository accountRepository;

  @Test
  @DisplayName("should create an account with success")
  void testCreateAccountWithSuccess() {
    var request = new HttpEntity<>(new AccountInput("73502752001"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(location).containsSequence("/accounts/");
  }

  @Test
  @DisplayName("should fail the creation of the account when document not informed")
  void testDocumentNotInformedToCreateAccount() {
    var request = new HttpEntity<>(new AccountInput(""), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail the creation of the account when document invalid")
  void testDocumentInvalidToCreateAccount() {
    var request = new HttpEntity<>(new AccountInput("1234567890"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail to existing account already registered")
  void testExistingAccount() {
    accountRepository.save(new AccountEntity("97242904099"));

    var request = new HttpEntity<>(new AccountInput("97242904099"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  @DisplayName("should return the data an account")
  void testFindAccountWihSuccess() {
    var account = accountRepository.save(new AccountEntity("37995834080"));
    var id = account.getId();

    var response = restTemplate.getForEntity("/accounts/" + id, AccountOutput.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(id);
    assertThat(response.getBody().getDocumentNumber()).isEqualTo(account.getDocumentNumber());
  }

  @Test
  @DisplayName("should return the data an account")
  void testFindAccountNotFound() {
    var response = restTemplate.getForEntity("/accounts/0", AccountOutput.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
