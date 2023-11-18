package br.com.brunopaese.transelevador.model;

import br.com.brunopaese.transelevador.controller.AdressService;

public class Adress {

    private int id;
    private int axisX;
    private int axisY;
    private int axisZ;
    private WarehouseName warehouseName;

    public Adress(int id) {
        this.id = id;
    }

    public Adress() {
    }

    public void setWarehouseName(int idProduct) {
        int quantityCassioli = new AdressService().sumQuantityOfProducts(idProduct, WarehouseName.CASSIOLI);
        int quantityDematic = new AdressService().sumQuantityOfProducts(idProduct, WarehouseName.DEMATIC);
        if (quantityDematic < quantityCassioli) {
            warehouseName = WarehouseName.DEMATIC;
        } else {
            warehouseName = WarehouseName.CASSIOLI;
        }
    }

    public int getId() {
        return id;
    }

    public int getAxisX() {
        return axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public int getAxisZ() {
        return axisZ;
    }

    public WarehouseName getWarehouseName() {
        return warehouseName;
    }
}
