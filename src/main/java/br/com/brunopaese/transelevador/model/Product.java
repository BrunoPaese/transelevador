package br.com.brunopaese.transelevador.model;

import br.com.brunopaese.transelevador.controller.ReferenceException;
import br.com.brunopaese.transelevador.controller.WeightException;

public class Product {

    private int idProduct;
    private String description;
    private double unitWeight;
    private boolean inUse;

    public Product(int idProduct, String description, double unitWeight) {
        this.idProduct = idProduct;
        if (String.valueOf(idProduct).length() != 5) {
            throw new ReferenceException("Referência inválida");
        }
        this.description = description;
        this.unitWeight = unitWeight;
        if (unitWeight == 0) {
            throw new WeightException("Peso inválido");
        }
        this.inUse = true;
    }

    public Product(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitWeight() {
        return unitWeight;
    }

    public boolean isInUse() {
        return inUse;
    }
}
