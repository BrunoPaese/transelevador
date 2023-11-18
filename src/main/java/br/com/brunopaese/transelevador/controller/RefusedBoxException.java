package br.com.brunopaese.transelevador.controller;

public class RefusedBoxException extends RuntimeException {

    public RefusedBoxException(String message) {
        super(message);
    }

    public RefusedBoxException(String message, Throwable cause) {
        super(message);
    }

}
