package com.adp.coinChange.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String SOMETHING_WENT_WRONG = "Internal Server Error. Something went wrong";

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorMessage> handleInvalidAmountException(InvalidAmountException e) {
        return ResponseEntity.badRequest().
                body(
                        ErrorMessage
                                .builder()
                                .message(e.getMessage())
                                .code("EC-2")
                                .build());
    }

    @ExceptionHandler(AmountParseException.class)
    public ResponseEntity<ErrorMessage> handleAmountParseException(AmountParseException e) {
        return ResponseEntity.badRequest().
                body(
                        ErrorMessage
                                .builder()
                                .message(e.getMessage())
                                .code("EC-3")
                                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleServiceExceptions(Exception e) {
        return ResponseEntity.internalServerError().
                body(
                        ErrorMessage
                                .builder()
                                .message(SOMETHING_WENT_WRONG)
                                .code("EC-1")
                                .build());
    }
}
