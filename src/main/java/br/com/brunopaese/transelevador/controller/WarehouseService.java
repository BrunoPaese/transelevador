package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.DAO.ProductDAO;
import br.com.brunopaese.transelevador.DAO.WarehouseDAO;
import br.com.brunopaese.transelevador.model.Product;

import java.sql.Connection;

public class WarehouseService {

    private ConnectionFactory connection;

    public WarehouseService() {
        this.connection = new ConnectionFactory();
    }

    public void storeBox(int reference, int quantityOfProducts) {
        Connection conn = connection.recoverConnection();
        new WarehouseDAO(conn).storeBox(reference, quantityOfProducts);
    }

    public void removeBox() {
        Connection conn = connection.recoverConnection();
        //new WarehouseDAO(conn).removeBox(reference, quantityOfBoxes);
    }

    public void actionChanges(int reference, String description, double unitWeight, boolean inUse) {
        Connection conn = connection.recoverConnection();
        new ProductDAO(conn).changeProduct(reference, description, unitWeight, inUse);
    }



}
