package br.com.brunopaese.transelevador.DAO;

import br.com.brunopaese.transelevador.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO  {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void registerProduct(int idProduct, String description, double unitWeight) {
        var product = new Product(idProduct, description, unitWeight);
        String sql = "insert into product(id, weight, description, in_use) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getIdProduct());
            preparedStatement.setDouble(2, product.getUnitWeight());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBoolean(4, product.isInUse());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void changeProduct(int reference, String description, double unitWeight, boolean inUse) {
        String sql = "update product set description = ?, weight = ?, in_use = ? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setDouble(2, unitWeight);
            preparedStatement.setBoolean(3, inUse);
            preparedStatement.setInt(4, reference);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

