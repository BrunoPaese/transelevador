package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.BoxDAO;
import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.DAO.ProductDAO;
import br.com.brunopaese.transelevador.model.Box;
import br.com.brunopaese.transelevador.model.Product;

import java.sql.Connection;

public class BoxService {

    private ConnectionFactory connection;

    public BoxService() {
        this.connection = new ConnectionFactory();
    }

    public Box searchById(int idBox) {
        Connection conn = connection.recoverConnection();
        Box box = new BoxDAO(conn).listBoxById(idBox);
        return box;
    }



}
