package com.adp.coinChange.Exception;

public class InvalidAmountException extends CoinChangeServiceException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
