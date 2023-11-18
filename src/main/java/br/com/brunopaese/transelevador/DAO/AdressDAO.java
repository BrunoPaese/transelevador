package br.com.brunopaese.transelevador.DAO;

import br.com.brunopaese.transelevador.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdressDAO {

    private Connection connection;

    public AdressDAO() {
    }

    public AdressDAO(Connection connection) {
        this.connection = connection;
    }

    public int calculatesSmallerQuantityProducts(int productReference, WarehouseName warehouseName) {
        String sql = "select sum(quantity_of_products) from warehouse_inventory where id_product = ? and id_warehouse = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int totalQuantityProducts;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productReference);
            preparedStatement.setInt(2, warehouseName.getIndex());
            resultSet = preparedStatement.executeQuery();
            totalQuantityProducts = resultSet.getRow();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalQuantityProducts;
    }

    public Adress freeCompartmentSearch() {
        String sql = "select min(id) from compartment where situation = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Adress adress = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Situation.FREE.name());
            resultSet = preparedStatement.executeQuery();
            int id = resultSet.getInt(1);
            adress = new Adress(id);
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return adress;
    }

}
