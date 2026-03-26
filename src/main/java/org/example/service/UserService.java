package org.example.service;

import org.example.dao.UserDAO;
import org.example.database.ConnectionFactory;
import org.example.model.PageResponse;
import org.example.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private static UserDAO userDAO = new UserDAO();

    public PageResponse<User> list(int page, int limit) throws SQLException {

        Connection conn = ConnectionFactory.connection();

        return userDAO.list(conn, page, limit);
    }
}
