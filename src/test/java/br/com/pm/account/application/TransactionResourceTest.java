package br.com.pm.account.application;

import br.com.pm.account.application.dto.TransactionRequest;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.domain.repositories.TransactionRepository;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TransactionResourceTest {
  private static final int BUY_CASH_ID = 1;
  private static final int PAYMENT = 4;

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private AccountRepository accountRepository;
  @Autowired private TransactionRepository transactionRepository;

  @Test
  @DisplayName("should create an transaction buy the cash with success")
  void testCreateAccountWithSuccess() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
        new HttpEntity<>(
            new TransactionRequest(account.getId(), BUY_CASH_ID, 550.55), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(location).containsSequence("/transactions/");
  }

  @Test
  @DisplayName("should fail when the account not found")
  void testFailTransactionToAccountNotFound() {
    var accountId = 1000L;

    var request =
        new HttpEntity<>(new TransactionRequest(accountId, BUY_CASH_ID, 550.55), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @Test
  @DisplayName("should fail when the account not found")
  void testFailTransactionToOperationNotFound() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));
    var operationNotFound = 5;

    var request =
        new HttpEntity<>(
            new TransactionRequest(account.getId(), operationNotFound, 550.55), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @Test
  @DisplayName("should fail when the amount transaction is null")
  void testFailTransactionToAmountNull() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
        new HttpEntity<>(
            new TransactionRequest(account.getId(), BUY_CASH_ID, null), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail when the amount transaction is negativo")
  void testFailTransactionToAmountNegative() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
        new HttpEntity<>(
            new TransactionRequest(account.getId(), BUY_CASH_ID, -500.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail when the account id is null")
  void testFailTransactionToAccountIdNull() {
    var request =
        new HttpEntity<>(new TransactionRequest(null, BUY_CASH_ID, 55.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail when the operation id id is negativo")
  void testFailTransactionToAccountIdNegative() {
    var accountIdNegative = -1L;

    var request =
        new HttpEntity<>(
            new TransactionRequest(accountIdNegative, BUY_CASH_ID, 54.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail when the operation type id is null")
  void testFailTransactionToOperationTypeIdNull() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
        new HttpEntity<>(new TransactionRequest(account.getId(), null, 55.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should fail when the operation type id is negative")
  void testFailTransactionToOperationTypeIdNegative() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));
    var operationTypeNegative = -1;

    var request =
            new HttpEntity<>(new TransactionRequest(account.getId(), operationTypeNegative, 55.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("should save the amount negative for operation different payment")
  void testSaveTransactionAmountNegativeForOperationDifferentOfPayment() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
            new HttpEntity<>(
                    new TransactionRequest(account.getId(), BUY_CASH_ID, 659.55), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(location).containsSequence("/transactions/");

    var transaction = transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "eventDate")).stream().findFirst().get();

    assertThat(transaction.getAmount()).isEqualTo(-659.55);
  }

  @Test
  @DisplayName("should save the amount positive for operation payment")
  void testSaveTransactionAmountPositiveForOperationPayment() {
    var account = accountRepository.saveAndFlush(new AccountEntity("64002480054"));

    var request =
            new HttpEntity<>(
                    new TransactionRequest(account.getId(), PAYMENT, 300.00), new HttpHeaders());
    var response = restTemplate.postForEntity("/transactions", request, Void.class);

    var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(location).containsSequence("/transactions/");

    var transaction = transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "eventDate")).stream().findFirst().get();

    assertThat(transaction.getAmount()).isEqualTo(300.00);
  }
}
