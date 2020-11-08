package br.com.pm.account.application;

import br.com.pm.account.application.dto.TransactionRequest;
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
class TransactionResourceTest {
    private static Integer OPERATION_BUY_CASH = 1;

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private AccountRepository accountRepository;

    @Test
    @DisplayName("should create an transaction buy the cash with success")
    void testCreateAccountWithSuccess() {
        var account = accountRepository.save(new AccountEntity("64002480054"));

        var request = new HttpEntity<>(new TransactionRequest(account.getId(), OPERATION_BUY_CASH, 550.55), new HttpHeaders());
        var response = restTemplate.postForEntity("/transactions", request, Void.class);

        var location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(location).containsSequence("/transactions/");

        accountRepository.deleteAll();
    }

}