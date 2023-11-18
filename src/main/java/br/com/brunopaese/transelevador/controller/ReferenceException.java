package br.com.brunopaese.transelevador.controller;

public class ReferenceException extends RuntimeException {

    public ReferenceException(String message) {
        super(message);
    }

    public ReferenceException(String message, Throwable cause) {
        super(message);
    }

}
