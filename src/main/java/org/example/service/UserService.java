package org.example.service;

import org.example.dao.UserDAO;
import org.example.database.ConnectionFactory;
import org.example.model.PageResponse;
import org.example.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private static UserDAO userDAO = new UserDAO();

    public void createUser(String userName,
                           String email) throws SQLException {

        Connection conn = ConnectionFactory.connection();

        userName = clearSpaces(userName);
        email = clearSpaces(email);

        verification(userName, email);

        userDAO.createUser(conn, userName, email);

    }

    public PageResponse<User> list(String userName,
                                   int page, int limit) throws SQLException {

        Connection conn = ConnectionFactory.connection();

        return userDAO.list(conn, userName, page, limit);
    }

    public static String clearSpaces(String ex) {

        return ex.trim();
    }

    public static void verification(String userName,
                                    String email) {

        if (!userName.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            throw new RuntimeException("Username is invalid.");
        } else if (email.isBlank()) {
            throw new RuntimeException("Email addresses cannot be empty.");
        } else if (!email.matches("^[^@\\s]+@[a-zA-Z0-9-]+\\.com$")) {
            throw new RuntimeException("Invalid email");
        }
    }
}
