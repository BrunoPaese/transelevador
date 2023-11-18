package br.com.brunopaese.transelevador.model;

public enum WarehouseName {

    CASSIOLI(1),
    DEMATIC(2);

    private int index;

    WarehouseName(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}
