package br.ufes.inf.nemo.smartcast.domain.exceptions;

public class InvalidFeedException extends Exception {

    public InvalidFeedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
