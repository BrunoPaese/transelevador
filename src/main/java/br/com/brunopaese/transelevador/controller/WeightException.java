package br.com.brunopaese.transelevador.controller;

public class WeightException extends RuntimeException {

    public WeightException(String message) {
        super(message);
    }

    public WeightException(String message, Throwable cause) {
        super(message);
    }

}
