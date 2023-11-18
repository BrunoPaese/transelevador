package br.com.brunopaese.transelevador.view;

import br.com.brunopaese.transelevador.DAO.AdressDAO;
import br.com.brunopaese.transelevador.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teste {



    public static void main(String[] args) {
        Connection connection;
        Connection conn = connection.recoverConnection();
            String sql = "select min(id) from compartment where situation = ?";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            Adress adress = null;
            try {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, Situation.FREE.name());
                resultSet = preparedStatement.executeQuery();
                int id = resultSet.getInt(1);
                adress = new Adress(id);
                resultSet.close();
                preparedStatement.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        System.out.println(adress.getId());
        }

    }



