package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.AdressDAO;
import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.model.Adress;
import br.com.brunopaese.transelevador.model.Product;
import br.com.brunopaese.transelevador.model.WarehouseName;

import java.sql.Connection;

public class AdressService {

    private ConnectionFactory connection;

    public AdressService() {
        this.connection = new ConnectionFactory();
    }

    public int sumQuantityOfProducts(int idProduct, WarehouseName warehouseName) {
        Connection conn = connection.recoverConnection();
        return new AdressDAO(conn).calculatesSmallerQuantityProducts(idProduct, warehouseName);
    }

    public Adress checkPlaceIsFree() {
        Connection conn = connection.recoverConnection();
        Adress adress = new AdressDAO(conn).freeCompartmentSearch();
        return adress;
    }












}
