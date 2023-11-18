package br.com.brunopaese.transelevador.model;

import java.util.Random;

public class Balance {

    public double readWeight() {
        int maximum = 70;
        int minimal = 1;
        return new Random().nextInt(maximum + minimal);
    }

}
