package br.com.brunopaese.transelevador.model;

import br.com.brunopaese.transelevador.controller.AdressService;

import java.util.*;

public class Warehouse {

    private List<Product> products = new ArrayList<>(); //alterar para map (chave-valor)
    private List<Box> boxes = new ArrayList<>();
    private Adress adress;
    private double weight;


    final int xAxisCapacity = 20;
    final int yAxisCapacity = 10;

    public void add(Product product) {
        this.products.add(product);
    }

    public void remove(Box box) {
        this.boxes.remove(box);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Box> getBoxes() {
        return Collections.unmodifiableList(boxes);
    }

}

