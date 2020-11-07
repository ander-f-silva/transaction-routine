package br.com.pm.account.domain.services.exception;

public class AccountAlreadyCreatedException extends RuntimeException {
    public AccountAlreadyCreatedException(String message) {
        super(message);
    }
}
