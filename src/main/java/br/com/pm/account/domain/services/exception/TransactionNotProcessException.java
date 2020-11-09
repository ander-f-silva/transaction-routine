package br.com.pm.account.domain.services.exception;

public class TransactionNotProcessException extends RuntimeException {
  public TransactionNotProcessException(String message) {
    super(message);
  }
}
