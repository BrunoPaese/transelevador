package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.ProductDAO;
import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.model.Product;

import java.sql.Connection;

public class ProductService {

    private ConnectionFactory connection;

    public ProductService() {
        this.connection = new ConnectionFactory();
    }

    public Product searchByReference(int reference) {
        Connection conn = connection.recoverConnection();
        Product product = new ProductDAO(conn).listProductByReference(reference);
        return product;
    }

    public void register(int reference, String description, double unitWeight) {
        Connection conn = connection.recoverConnection();
        new ProductDAO(conn).registerProduct(reference,description, unitWeight);
    }

    public void changes(int reference, String description, double unitWeight, boolean inUse) {
        Connection conn = connection.recoverConnection();
        new ProductDAO(conn).changeProduct(reference, description, unitWeight, inUse);
    }


}
