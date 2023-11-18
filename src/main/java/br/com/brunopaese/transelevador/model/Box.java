package br.com.brunopaese.transelevador.model;

public class Box {

    private int idBox;
    private double weight;
    private boolean reset;
    private Situation situation;

    public Box(int idBox, double weight, boolean reset, Situation situation) {
        this.idBox = idBox;
        this.weight = weight;
        this.reset = reset;
        this.situation = situation;
    }

    public Box(int idBox) {
        this.idBox = idBox;
    }

    public int getIdBox() {
        return idBox;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isReset() {
        return reset;
    }

    public Situation getSituation() {
        return situation;
    }
}



