package br.com.dicasdeumdev.api.services.exceptions;

public class DataIntregityViolationException extends RuntimeException {

    public DataIntregityViolationException(String message) {
        super(message);
    }

}