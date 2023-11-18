package br.com.brunopaese.transelevador.DAO;

import br.com.brunopaese.transelevador.controller.AdressService;
import br.com.brunopaese.transelevador.model.*;

import java.sql.*;

public class WarehouseDAO {

    private Connection connection;

    public WarehouseDAO(Connection connection) {
        this.connection = connection;
    }

    public void storeBox(int idProduct, int quantity) {
        Box box = new Box(new Reader().readReference());
        Adress adress = new AdressService().checkPlaceIsFree();
        String sql = "insert into warehouse_inventory(id_box, id_product, quantity_of_products, weight, id_compartment) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, box.getIdBox());
            preparedStatement.setInt(2, idProduct);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, new Balance().readWeight());
            preparedStatement.setInt(5, adress.getId());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeAdressSituation(int idAdress, Situation situation) {
        String sql = "update compartment set situation = ? where id_compartment = ? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(situation));
            preparedStatement.setInt(2, idAdress);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBox(int id) {

    }

    public Product listProductByReference(int idProduct) {
        String sql = "select * from product where id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProduct);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                double unitWeight = resultSet.getDouble(2);
                String description = resultSet.getString(3);
                product = new Product(id, description, unitWeight);
                product.setInUse(resultSet.getBoolean(4));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return product;
    }

}
