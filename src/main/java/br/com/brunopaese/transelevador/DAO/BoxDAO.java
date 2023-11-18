package br.com.brunopaese.transelevador.DAO;

import br.com.brunopaese.transelevador.model.Box;
import br.com.brunopaese.transelevador.model.Product;
import br.com.brunopaese.transelevador.model.Situation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoxDAO {

    private Connection connection;

    public BoxDAO(Connection connection) {
        this.connection = connection;
    }

    public Box listBoxById(int idBox) {
        String sql = "select * from box where id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Box box = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idBox);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                double weight = resultSet.getDouble(2);
                boolean description = resultSet.getBoolean(3);
                String situation = resultSet.getString(4);
                box = new Box(id, weight, description, Situation.valueOf(situation));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return box;
    }

}

