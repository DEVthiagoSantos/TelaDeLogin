package org.example.database;

import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static String url = System.getenv("DB_URL");
    private static String user = System.getenv("DB_USER");
    private static String password = System.getenv("DB_PASSWORD");

    public static Connection connection() throws DatabaseException {

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException es) {
            throw new DatabaseException("Erro ao salvar usuário no banco de dados", es);
        }

    }
}
