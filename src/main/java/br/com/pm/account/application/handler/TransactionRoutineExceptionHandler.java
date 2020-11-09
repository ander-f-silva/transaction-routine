package br.com.pm.account.application.handler;

import br.com.pm.account.application.dto.MessageResponse;
import br.com.pm.account.domain.services.exception.AccountAlreadyCreatedException;
import br.com.pm.account.domain.services.exception.AccountNotFoundException;
import br.com.pm.account.domain.services.exception.TransactionNotProcessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "br.com.pm.account")
class TransactionRoutineExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(AccountAlreadyCreatedException.class)
  ResponseEntity<?> handleConflict(RuntimeException runtimeException, WebRequest request) {
    return new ResponseEntity<>(
        new MessageResponse(HttpStatus.CONFLICT.value(), runtimeException.getMessage()),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AccountNotFoundException.class)
  ResponseEntity<?> handleNotFound(RuntimeException runtimeException, WebRequest request) {
    return new ResponseEntity<>(
        new MessageResponse(HttpStatus.NOT_FOUND.value(), runtimeException.getMessage()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TransactionNotProcessException.class)
  ResponseEntity<?> handleUnprocessableEntity(
      RuntimeException runtimeException, WebRequest request) {
    return new ResponseEntity<>(
        new MessageResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), runtimeException.getMessage()),
        HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
