package br.com.brunopaese.transelevador.model;

import java.util.Random;

public class Reader {

    public int readReference() {
        int maximum = 100;
        int minimal = 1;
        return new Random().nextInt(maximum + minimal);
    }

}
