package br.ufes.inf.nemo.smartcast.domain.exceptions;

public class MalformedFeedException extends Exception {

    public MalformedFeedException(String message) {
        super(message);
    }

    public MalformedFeedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
