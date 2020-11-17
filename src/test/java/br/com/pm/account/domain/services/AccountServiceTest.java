package br.com.pm.account.domain.services;

import br.com.pm.account.application.dto.AccountInput;
import br.com.pm.account.domain.repositories.AccountRepository;
import br.com.pm.account.infrastructure.jpa.entities.AccountEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
  public static final String DOCUMENT_CPF = "95051245030";

  @InjectMocks AccountOperation accountOperation = new AccountService();

  @Mock AccountRepository accountRepository;

  @Test
  @DisplayName("should create an account with success")
  void shouldCreateAnAccountWithSuccess() {
    Long expectedAccountId = 1L;
    String expectedMessageEmpty = "";

    when(accountRepository.existsByDocumentNumber(eq(DOCUMENT_CPF))).thenReturn(false);
    when(accountRepository.save(any(AccountEntity.class)))
        .thenReturn(new AccountEntity(expectedAccountId, DOCUMENT_CPF));

    var informationAccount = accountOperation.createNewAccount(new AccountInput(DOCUMENT_CPF));

    verify(accountRepository, times(1)).save(any(AccountEntity.class));
    verify(accountRepository, times(1)).existsByDocumentNumber(eq(DOCUMENT_CPF));

    assertThat(informationAccount).isNotNull();
    assertThat(informationAccount.getInput()).isNotNull();
    assertThat(informationAccount.getError()).isNotNull();

    assertThat(expectedAccountId).isEqualTo(informationAccount.getInput());
    assertThat(expectedMessageEmpty).isEqualTo(informationAccount.getError().getMessage());
  }

  @Test
  @DisplayName("should account already registered")
  void shouldAccountAlreadyRegistered() {
    var expectedAccountId = 0L;
    String expectedMessageError = "An account already exists with the data reported";

    when(accountRepository.existsByDocumentNumber(eq(DOCUMENT_CPF))).thenReturn(true);

    var informationAccount = accountOperation.createNewAccount(new AccountInput(DOCUMENT_CPF));

    verify(accountRepository, times(1)).existsByDocumentNumber(eq(DOCUMENT_CPF));
    verify(accountRepository, times(0)).save(any(AccountEntity.class));

    assertThat(informationAccount).isNotNull();
    assertThat(informationAccount.getInput()).isNotNull();
    assertThat(informationAccount.getError()).isNotNull();

    assertThat(expectedAccountId).isEqualTo(informationAccount.getInput());
    assertThat(expectedMessageError).isEqualTo(informationAccount.getError().getMessage());
  }
}
