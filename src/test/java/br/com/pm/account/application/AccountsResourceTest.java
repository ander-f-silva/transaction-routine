package br.com.pm.account.application;

import br.com.pm.account.application.dto.AccountRequest;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountsResourceTest {
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private AccountRepository accountRepository;

  @Test
  @DisplayName("should create an account with success")
  void testCreateAccountWithSuccess() {
    var request = new HttpEntity<>(new AccountRequest("73502752001"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(location).containsSequence("/accounts/");
  }

  @Test
  @DisplayName("should fail the creation of the account when document not informed")
  void testDocumentNotInformedToCreateAccount() {
    var request = new HttpEntity<>(new AccountRequest(""), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail the creation of the account when document invalid")
  void testDocumentInvalidToCreateAccount() {
    var request = new HttpEntity<>(new AccountRequest("1234567890"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail to existing account already registered")
  void testExistingAccount() {
    accountRepository.save(new AccountEntity("97242904099"));

    var request = new HttpEntity<>(new AccountRequest("97242904099"), new HttpHeaders());
    var response = restTemplate.postForEntity("/accounts", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }
}
