package br.com.brunopaese.transelevador.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection connection;

    public Connection recoverConnection() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("*");
        config.setUsername("*");
        config.setPassword("*");
        config.setMaximumPoolSize(10);
        return new HikariDataSource(config);
    }
}